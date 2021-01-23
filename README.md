![Version](https://img.shields.io/badge/version-1.0.1-yellow)
![Version](https://img.shields.io/badge/milestones-2-brightgreen)


# Tip_Time: 小费计数app

<img src="app/src/main/res/picture/01.jpeg" width="30.5%" height="30.5%">

### 结构
仅用MainActivity写逻辑声明


### 步骤
1. 绑定对象实例，可以访问xml的视图
`private lateinit var binding: ActivityMainBinding`

2. 重写onCreate方法
初始化binding用于访问xml的UI元件（View对象）
`binding = ActivityMainBinding.inflate(layoutInflater)`

设置root（根/所有）将内容视图设置为activity（活动）的布局的根视图
`setContentView(binding.root)`

在按钮上设置点击监听器来执行计算小费的方法 calculateTip()
(```)
binding.button.setOnClickListener {
            calculateTip()
        }
(```)

3.
