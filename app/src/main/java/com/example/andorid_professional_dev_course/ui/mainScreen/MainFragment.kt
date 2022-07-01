package com.example.andorid_professional_dev_course.ui.mainScreen

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.splashscreen.SplashScreen
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.andorid_professional_dev_course.data.MainScreenData.ResultDTO
import com.example.andorid_professional_dev_course.databinding.FragmentMainBinding
import org.koin.android.ext.android.getKoin
import org.koin.core.qualifier.named

class MainFragment(private val splashScreen: SplashScreen) : Fragment() {
    private val mainScreenAdapter = MainScreenAdapter()
    private val scope by lazy { getKoin().getOrCreateScope("", named("Project_scope")) }

    //    private val usecase: ProjectUsecase.MainScreenUsecase by inject(named("MainScreenUsecaseImpl"))
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        splashScreen.setKeepOnScreenCondition { true }
        val viewModel = ViewModelProvider(
            this,
            MainScreenViewModel(scope.get(named("MainScreenUsecaseImpl")))
        ).get(MainScreenViewModel::class.java)
        lateinit var resultDTO: ResultDTO
        viewModel.showLanguages()

        viewModel.spinnerList.observe(viewLifecycleOwner) {
            binding.spinnerList.adapter =
                ArrayAdapter(requireContext(), R.layout.simple_spinner_dropdown_item, it)
            splashScreen.setKeepOnScreenCondition { false }
        }

        binding.translateBtn.setOnClickListener {
            binding.materialView.isChecked = false
            viewModel.showTranslation(
                binding.spinnerList.selectedItem.toString(),
                binding.editText.text.toString()
            )
            viewModel.resultDTO.observe(viewLifecycleOwner) {
                if (it.def.isNotEmpty()) {
                    binding.materialView.isClickable = true
                    resultDTO = it
                    if (it.def.first().tr.first().syn == null) {
                        mainScreenAdapter.list = emptyList()
                    } else {
                        it.def.first().tr.first().syn?.let { listSyn ->
                            mainScreenAdapter.list = listSyn
                        }
                    }
                    binding.insideLayout.inside.visibility = View.VISIBLE
                    binding.insideLayout.translateResult.text = it.def.first().tr.first().text
                    binding.insideLayout.posResult.text = it.def.first().pos
                    binding.insideLayout.recyclerView.apply {
                        layoutManager = LinearLayoutManager(context)
                        adapter = mainScreenAdapter
                    }
                } else {
                    Toast.makeText(requireContext(), "No result", Toast.LENGTH_SHORT).show()
                    binding.insideLayout.inside.visibility = View.GONE
                }

            }
        }

        binding.materialView.setOnLongClickListener {
            if (binding.materialView.isClickable) {
                binding.materialView.isChecked = true
                viewModel.insertWord(resultDTO)
                Toast.makeText(requireContext(), "Added", Toast.LENGTH_SHORT).show()
            }
            true
        }


    }

    override fun onDestroyView() {
        scope.close()
        super.onDestroyView()
        _binding = null

    }

}