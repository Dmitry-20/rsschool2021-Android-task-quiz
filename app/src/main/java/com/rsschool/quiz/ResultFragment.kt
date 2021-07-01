package com.rsschool.quiz

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rsschool.quiz.databinding.FragmentResultBinding


class ResultFragment : Fragment() {

    private var binding: FragmentResultBinding? = null
    private var quizInterf: QuizInterf? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentResultBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAction()
        initView()
    }

    private fun initView() {
        binding?.result?.text = quizInterf?.getResult()
    }

    private fun initAction() {
        binding?.close?.setOnClickListener {
            quizInterf?.closeApp()
        }

        binding?.back?.setOnClickListener {
            quizInterf?.update()
        }
        binding?.share?.setOnClickListener {
            quizInterf?.sendResult()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            ResultFragment().apply {
            }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (activity is QuizInterf) {
            quizInterf = activity as QuizInterf
        } else {
            throw RuntimeException(activity.toString() + " must implement HostQuiz interface")
        }
    }


    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    override fun onDetach() {
        super.onDetach()
        quizInterf = null
    }
}