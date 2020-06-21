package dasdsa.sdn.numberbaseballgame_20200621.adapters

import android.content.Context
import android.content.Intent
import android.media.JetPlayer
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import dasdsa.sdn.numberbaseballgame_20200621.R
import dasdsa.sdn.numberbaseballgame_20200621.datas.Chat
import org.json.JSONObject

class ChatAdapter(
    val mContext:Context,
    val resId:Int,
    val mList:List<Chat>) : ArrayAdapter<Chat>(mContext, resId, mList) {

    val inf = LayoutInflater.from(mContext)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var tempRow = convertView

        tempRow?.let {

        }.let {
            tempRow = inf.inflate(R.layout.chat_list_item, null)
        }
        val row = tempRow!!


        return row
    }

}