package dasdsa.sdn.numberbaseballgame_20200621

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import dasdsa.sdn.numberbaseballgame_20200621.adapters.ChatAdapter
import dasdsa.sdn.numberbaseballgame_20200621.datas.Chat
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.log

class MainActivity : BaseActivity() {

    val cpuNumList = ArrayList<Int>()

    val chatList = ArrayList<Chat>()

    lateinit var mChatAdapter : ChatAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        inputBtn.setOnClickListener {
            val inputNum = numberEdt.text.toString()

            if(inputNum.length != 3) {
                Toast.makeText(mContext, "세자리 숫자만 입력 가능합니다.", Toast.LENGTH_SHORT).show()
                //이벤트 강제종료
                 return@setOnClickListener
            }


            chatList.add(Chat("ME", inputNum))

            numberEdt.setText("")

            CheckUserInputStrikeAndBall(inputNum)
        }
    }

    fun CheckUserInputStrikeAndBall(input : String) {

        val number = input.toInt() //"256" => 256
        val numArr = ArrayList<Int>()
        numArr.add( number / 100)   //100의자리 2 : 256/100 = 2.56
        numArr.add( number/10 % 10)    //10의자리 5
        numArr.add( number % 10)    //1의자리 6

        var strikeCount = 0
        var ballCount = 0


        for ( i in numArr.indices) {

            for ( j in cpuNumList.indices) {

                if (numArr[i] == cpuNumList[j] ){
                    if ( i == j )
                    {
                        strikeCount ++
                    }
                    else {
                        ballCount ++
                    }
                }

            }
        }


        chatList.add(Chat("CPU", "${strikeCount}S ${ballCount}B 입니다."))

        if ( strikeCount == 3) {
            chatList.add(Chat("CPU", "축하합니다.") )

            Toast.makeText(mContext, "게임을 종료 합니다.", Toast.LENGTH_SHORT).show()

            //입력도 못하도록 막자.
            numberEdt.isEnabled = false
            inputBtn.isEnabled = false
        }

    }

    override fun setValues() {

        makeQuestionNum()

        for (num in cpuNumList ){
            Log.d("문제 출제", num.toString())
        }
        chatList.add(Chat("CPU", "숫자 야구게임에 오신것을 환영합니다."))
        chatList.add(Chat("CPU", "세자리 숫자를 맞춰 주세요."))
        chatList.add(Chat("CPU", "0은 포함되지 않으며, 중복된 숫자도 없습니다."))

        //CharAdapter
        mChatAdapter = ChatAdapter(mContext, R.layout.chat_list_item, chatList)
        chatListView.adapter = mChatAdapter

    }

    fun makeQuestionNum() {


        for (i in 0..2) {

            while (true) {

                val randomNum = (Math.random()*9 + 1).toInt()


                var duplCheckResult = true

                for (num in cpuNumList) {
                    if (num == randomNum){

                        duplCheckResult = false
                    }

                }

                if (duplCheckResult) {

                    cpuNumList.add(randomNum)

                    break
                }

            }

        }


    }

}
