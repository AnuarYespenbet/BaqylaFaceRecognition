package kz.anuaryespenbet.baqylafacerecognition.view.result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_result.*
import kz.anuaryespenbet.baqylafacerecognition.R

open class ResultFragment : Fragment() {
    lateinit var resultViewModel: ResultViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_result, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        resultViewModel = ViewModelProvider(this).get(ResultViewModel::class.java)

        result_btn.setOnClickListener {
            resultViewModel.clearAll()
            findNavController().navigateUp()
        }
    }
}