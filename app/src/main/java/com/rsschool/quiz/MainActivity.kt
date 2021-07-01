package com.rsschool.quiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.rsschool.quiz.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), QuizInterf {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewPagerAdapter: ViewPagerAdapter
    private lateinit var fragments: List<Fragment>

    private var userAnswers = mutableListOf(0, 0, 0, 0, 0)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    override fun onBackPressed() {
        if (binding.viewPager2.currentItem == 0 || binding.viewPager2.currentItem == 5) {
            finish()
        }
        binding.viewPager2.currentItem = binding.viewPager2.currentItem - 1
    }

    private fun initViews() {
        fragments = initFragments()
        viewPagerAdapter = ViewPagerAdapter(fragments, this)
        binding.viewPager2.adapter = viewPagerAdapter
        binding.viewPager2.isUserInputEnabled = false
    }

    private fun initFragments(): List<Fragment> {
        return listOf(
            QuizFragment.newInstance(0, questions[0], R.style.First, statusBarColors[0]),
            QuizFragment.newInstance(1, questions[1], R.style.Second, statusBarColors[1]),
            QuizFragment.newInstance(2, questions[2], R.style.Third, statusBarColors[2]),
            QuizFragment.newInstance(3, questions[3], R.style.Fourth, statusBarColors[3]),
            QuizFragment.newInstance(4, questions[4], R.style.Fifth, statusBarColors[4]),
            ResultFragment.newInstance()
        )
    }

    override fun leafFragment(forward: Boolean) {
        if (forward) {
            binding.viewPager2.currentItem = binding.viewPager2.currentItem + 1
        } else {
            binding.viewPager2.currentItem = binding.viewPager2.currentItem - 1
        }
    }

    override fun update() {
        initViews()
    }

    override fun sendResult() {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"

        val result = StringBuilder()
        result.append(getResult())
        result.append("\n\n")

        for ((index, item) in questions.withIndex()) {
            result.append(index + 1)
            result.append(") ")
            result.append(item.question)
            result.append("\n")
            result.append("Your answer: ")
            result.append(item.answers[userAnswers[index]])
            result.append("\n\n")
        }

        intent.putExtra(Intent.EXTRA_SUBJECT, "Quiz results")
        intent.putExtra(Intent.EXTRA_TEXT, result.toString())
        startActivity(intent)
    }


    override fun closeApp() {
        finish()
    }

    override fun getResult(): String {
        var count = 0
        for (index in userAnswers.indices) {
            if (userAnswers[index] == rightAnswers[index]) {
                count++
            }
        }
        return "Your result: $count of 5"    }

    override fun userAnswer(position: Int, answerID: Int) {
        userAnswers[position] = answerID
    }
}