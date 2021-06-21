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
    private var hostQuiz: HostQuiz? = null

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
        binding?.result?.text = hostQuiz?.getResult()
    }

    private fun initAction() {
        binding?.close?.setOnClickListener {
            hostQuiz?.closeApp()
        }

        binding?.back?.setOnClickListener {
            hostQuiz?.update()
        }
        binding?.share?.setOnClickListener {
            hostQuiz?.sendResult()
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
        if (activity is HostQuiz) {
            hostQuiz = activity as HostQuiz
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
        hostQuiz = null
    }
}