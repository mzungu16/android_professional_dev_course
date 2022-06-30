package com.example.andorid_professional_dev_course.ui.dictionaryScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.andorid_professional_dev_course.databinding.ActivityDictionaryBinding
import com.example.andorid_professional_dev_course.domain.ProjectUsecase
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.android.inject
import org.koin.core.qualifier.named

class DictionaryFragment : Fragment(),DictionaryAdapter.OnItemClick {
    private val dAdapter = DictionaryAdapter(this)
    private val scope by lazy { getKoin().getOrCreateScope("", named("Project_scope")) }
//    private val usecase: ProjectUsecase.DictionaryUsecase by inject(named("DictionaryScreenUsecaseImpl"))
    private var _binding: ActivityDictionaryBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ActivityDictionaryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = ViewModelProvider(
            this,
            DictionaryViewModel(scope.get(named("DictionaryScreenUsecaseImpl")))
        ).get(DictionaryViewModel::class.java)

        viewModel.getWords()

        viewModel.listOfWord.observe(viewLifecycleOwner) {
            dAdapter.list = it
        }

        binding.recyclerViewDic.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = dAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onWordClick(position: Int) {
        //todo
    }
}