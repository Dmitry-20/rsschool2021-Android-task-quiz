package com.rsschool.quiz

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.rsschool.quiz.databinding.FragmentQuizBinding


class QuizFragment : Fragment() {

    private var binding: FragmentQuizBinding? = null

    private var position: Int = 0
    private var theme: Int = 0
    private var statusBarColor: Int = 0
    private var resursQuiz: ResursQuiz? = null
    private var quizInterf: QuizInterf? = null

    private var answerID: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            theme = it.getInt(THEME)
            position = it.getInt(POSITION)
            statusBarColor = it.getInt(STATUS_BAR_COLOR)
            resursQuiz = it.getSerializable(QUESTION) as ResursQuiz?
        }
    }

    override fun onDetach() {
        super.onDetach()
        quizInterf = null
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (activity is QuizInterf) {
            quizInterf = activity as QuizInterf
        } else {
            throw RuntimeException(activity.toString() + " must implement HostQuiz interface")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        requireContext().setTheme(theme)
        binding = FragmentQuizBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onResume() {
        super.onResume()
        requireActivity().window?.statusBarColor = ResourcesCompat
            .getColor(resources, statusBarColors[position], null)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initActions()
    }


    private fun initActions() {
        binding?.nextButton?.setOnClickListener {
            quizInterf?.userAnswer(position, answerID)
            quizInterf?.leafFragment(true)
        }
        binding?.previousButton?.setOnClickListener {
            quizInterf?.leafFragment(false)
        }
        binding?.toolbar?.setNavigationOnClickListener {
            quizInterf?.leafFragment(false)
        }

        binding?.radioGroup?.setOnCheckedChangeListener { _, checkedId ->
            run {
                binding?.nextButton?.isEnabled = true
                when (checkedId) {
                    binding?.optionOne?.id -> answerID = 0
                    binding?.optionTwo?.id -> answerID = 1
                    binding?.optionThree?.id -> answerID = 2
                    binding?.optionFour?.id -> answerID = 3
                    binding?.optionFive?.id -> answerID = 4
                }
            }
        }
    }

    private fun initViews() {
        binding?.nextButton?.isEnabled = binding?.radioGroup?.checkedRadioButtonId != -1

        binding?.toolbar?.title =
            requireContext().getString(R.string.question) + " " + (position.plus(1))
        if (position == 0) {
            binding?.toolbar?.navigationIcon = null
            binding?.previousButton?.isEnabled = false
        }
        if (position == 4) binding?.nextButton?.text = requireContext().getString(R.string.submit)
        binding?.question?.text = resursQuiz?.question

        binding?.optionOne?.text = resursQuiz?.answers?.get(0)
        binding?.optionTwo?.text = resursQuiz?.answers?.get(1)
        binding?.optionThree?.text = resursQuiz?.answers?.get(2)
        binding?.optionFour?.text = resursQuiz?.answers?.get(3)
        binding?.optionFive?.text = resursQuiz?.answers?.get(4)

    }

    companion object {
        @JvmStatic
        fun newInstance(position: Int, resursQuiz: ResursQuiz, theme: Int, statusBarColor: Int) =
            QuizFragment().apply {
                arguments = Bundle().apply {
                    putInt(POSITION, position)
                    putInt(THEME, theme)
                    putInt(STATUS_BAR_COLOR, statusBarColor)
                    putSerializable(QUESTION, resursQuiz)
                }
            }

        private const val POSITION = "POSITION"
        private const val THEME = "THEME"
        private const val QUESTION = "QUESTION"
        private const val STATUS_BAR_COLOR = "STATUS_BAR_COLOR"
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}