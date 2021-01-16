package com.bytedance.tiptime

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.bytedance.tiptime.databinding.ActivityMainBinding
import java.lang.Math.ceil
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    // 绑定对象实例，可以访问activity_main.xml布局中的视图
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        // 初始化binding 用于访问activity_main.xml 布局的UI元件（Views对象）
        binding = ActivityMainBinding.inflate(layoutInflater)   // 记忆

        super.onCreate(savedInstanceState)

        // 设置root（根/所有）将内容视图设置胃activity（活动）的布局的根视图
        setContentView(binding.root)

        // 在按钮上设置点击监听器 —— 执行计算小费的方法 calculateTip()
        binding.button.setOnClickListener {
            calculateTip()
        }
    }

    // 得到输入的金额转化成成本cost
    private fun calculateTip() {
        val inputAmount = binding.costOfServiceEditText.text.toString()  // 将输入的结果改为String
        val cost = inputAmount.toDoubleOrNull()
        if (cost == null) {  // 检查是否为null
            displayTip(0.0)
            return
        }

        // 根据选择的按钮获取百分比
        val tipPercentage = when (binding.radioGroup.checkedRadioButtonId) {
            R.id.radioButton -> 0.21
            R.id.radioButton2 -> 0.16
            else -> 0.10
        }

        // 小费 = 成本 * 百分比（tip = cost * tipPercentage）
        var tip = cost * tipPercentage

        // 四舍五入（round）或取整数roundUp
        if (binding.switch1.isChecked) {
            tip = ceil(tip)
        }
        // 显示小费金额tipAmount
        binding.tipAmount.text = tip.toString()
        displayTip(tip)

        // 隐藏键盘
        hintKeyBoard()
    }

    // 显示小费的方法
    fun displayTip(tip: Double) {
        // 货币格式显示
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)  // 获取货币的实例
        binding.tipAmount.text = getString(R.string.tip_amount, formattedTip)
    }

    // 隐藏键盘的方法
    private fun hintKeyBoard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if (imm.isActive && currentFocus != null) {
            if (currentFocus!!.windowToken != null) {
                imm.hideSoftInputFromWindow(
                    currentFocus!!.windowToken,
                    InputMethodManager.HIDE_NOT_ALWAYS
                )
            }
        }
    }
}
