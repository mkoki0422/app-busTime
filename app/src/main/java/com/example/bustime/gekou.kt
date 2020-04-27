package com.example.bustime

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


import android.os.Handler
import android.widget.ImageButton
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_toukou.*
import java.util.*
import kotlin.concurrent.timer

/**
 * A simple [Fragment] subclass.
 */





class gekou : Fragment() {


    var sethour=21
    var setminute=11
    var setsecond=0
    var count=0


    var sethourR=11
    var setminuteR=11
    var setsecondR=0
    var countR=0


    var min=1000000000
    var minR=1000000000

    var limitsecond = 0
    var limitminute = 0
    var limithour = 0

    var limitsecondR = 0
    var limitminuteR = 0
    var limithourR = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment



        val data=gekou_data()


        cal_closest_time(13,data.time as Array<MutableMap<String, Any>>)//data.time.sizeでエラー

        cal_closest_timeR(13,data.timeR as Array<MutableMap<String, Any>>)


        val handler=Handler()
        timer(period = 100){

            val calendar=Calendar.getInstance()//リアルタイムの時間を取得、タイマーを使うことで毎秒更新するため、毎秒動く
            val hour=calendar.get(Calendar.HOUR_OF_DAY)
            val minute=calendar.get(Calendar.MINUTE)
            val second=calendar.get(Calendar.SECOND)





            cal_setTime_limitTime(count,data.time )

            cal_setTimeR_limitTimeR(countR,data.timeR)

            //Log.d("TA", countR.toString())



            val nextButton=view?.findViewById<ImageButton>(R.id.nextButton)
            val backButton=view?.findViewById<ImageButton>(R.id.backButton3)
            nextButton?.setOnClickListener{
                if(count<13){
                    count+=1
                }


            }
            backButton?.setOnClickListener{
                if(count>0){count-=1}
            }




            val nextButtonR=view?.findViewById<ImageButton>(R.id.nextButtonR)
            val backButtonR=view?.findViewById<ImageButton>(R.id.backButtonR)
            nextButtonR?.setOnClickListener{
                if(countR<13){
                    countR+=1}

            }
            backButtonR?.setOnClickListener{
                if(countR>0){
                    countR-=1}
            }




            handler.post {

               // val nowTime = view?.findViewById<TextView>(R.id.nowTime)




              //  nowTime?.text = "${hour}時${minute}分${second}秒"


                // fun textTime(time: Array<MutableMap<String, Any>>, count: Int, data_size:Int, limithour:Int, limitminute:Int,limitsecond:Int)
                textTime(data.time,count,13,limithour,limitminute,limitsecond)
                textTimeR(data.timeR,countR,13,limithourR,limitminuteR,limitsecondR)




            }

        }





        return inflater.inflate(R.layout.fragment_gekou, container, false)

    }

    fun cal_closest_time (size:Int,time:Array<MutableMap<String,Any>>){
        val calendar=Calendar.getInstance()//リアルタイムの時間を取得、タイマーを使うことで毎秒更新するため、毎秒動く
        val hour=calendar.get(Calendar.HOUR_OF_DAY)
        val minute=calendar.get(Calendar.MINUTE)
        val second=calendar.get(Calendar.SECOND)

        for (i in 0..size) {
            if ((time[i]["hour"] as Int) * 60 * 60 + (time[i]["minute"] as Int) * 60 - (hour * 60 * 60 + minute * 60) > 0 && min > (time[i]["hour"] as Int) * 60 * 60 + (time[i]["minute"] as Int) * 60) {
                min = (time[i]["hour"] as Int) * 60 * 60 + (time[i]["minute"] as Int) * 60

                count = i

            }

        }

    }

    fun cal_closest_timeR (size:Int,timeR:Array<MutableMap<String,Any>>){
        val calendar=Calendar.getInstance()//リアルタイムの時間を取得、タイマーを使うことで毎秒更新するため、毎秒動く
        val hour=calendar.get(Calendar.HOUR_OF_DAY)
        val minute=calendar.get(Calendar.MINUTE)
        val second=calendar.get(Calendar.SECOND)

        for (i in 0..size) {
            if ((timeR[i]["hour"] as Int) * 60 * 60 + (timeR[i]["minute"] as Int) * 60 - (hour * 60 * 60 + minute * 60) > 0 && minR > (timeR[i]["hour"] as Int) * 60 * 60 + (timeR[i]["minute"] as Int) * 60) {
                minR = (timeR[i]["hour"] as Int) * 60 * 60 + (timeR[i]["minute"] as Int) * 60

                countR = i

            }

        }

    }


    fun cal_setTime_limitTime(count:Int, time: Array<MutableMap<String, Any>>){
        val calendar=Calendar.getInstance()//リアルタイムの時間を取得、タイマーを使うことで毎秒更新するため、毎秒動く
        val hour=calendar.get(Calendar.HOUR_OF_DAY)
        val minute=calendar.get(Calendar.MINUTE)
        val second=calendar.get(Calendar.SECOND)



        min= (time[count]["hour"] as Int)*60*60+(time[count]["minute"] as Int)*60
        setsecond = min % 60
        setminute = min / 60 % 60
        sethour = min / (60 * 60) % 24

        var caltime=sethour*60*60+setminute*60+setsecond-hour*60*60-minute*60-second

        limitsecond = caltime % 60
        limitminute = caltime / 60 % 60
        limithour = caltime / (60 * 60) % 24

        if (caltime<0){//マイナスになった時点で次のバスを表示（ボタン操作と同じ様にする）
            caltime+=60*60*24
            limitsecond = caltime % 60
            limitminute = caltime / 60 % 60
            limithour = caltime / (60 * 60) % 24
        }
    }

    fun cal_setTimeR_limitTimeR(countR:Int,timeR: Array<MutableMap<String, Any>>){
        val calendar=Calendar.getInstance()//リアルタイムの時間を取得、タイマーを使うことで毎秒更新するため、毎秒動く
        val hour=calendar.get(Calendar.HOUR_OF_DAY)
        val minute=calendar.get(Calendar.MINUTE)
        val second=calendar.get(Calendar.SECOND)

        minR= (timeR[countR]["hour"] as Int)*60*60+(timeR[countR]["minute"] as Int)*60
        setsecondR = minR % 60
        setminuteR = minR / 60 % 60
        sethourR = minR / (60 * 60) % 24

        var caltimeR=sethourR*60*60+setminuteR*60+setsecondR-hour*60*60-minute*60-second

        limitsecondR = caltimeR % 60
        limitminuteR = caltimeR / 60 % 60
        limithourR = caltimeR / (60 * 60) % 24

        if (caltimeR<0){//マイナスになった時点で次のバスを表示（ボタン操作と同じ様にする）
            caltimeR+=60*60*24
            limitsecondR = caltimeR % 60
            limitminuteR = caltimeR / 60 % 60
            limithourR = caltimeR / (60 * 60) % 24
        }

    }

    fun textTime(time: Array<MutableMap<String, Any>>, count: Int, data_size:Int, limithour:Int, limitminute:Int,limitsecond:Int){
        val nextTime=view?.findViewById<TextView>(R.id.nextTime)
        val backTime=view?.findViewById<TextView>(R.id.backTime)
        //val nowTime = view?.findViewById<TextView>(R.id.nowTime)
        val limitTime=view?.findViewById<TextView>(R.id.limitTime)
        val busTime=view?.findViewById<TextView>(R.id.busTime)
        val place=view?.findViewById<TextView>(R.id.place)

        val calendar=Calendar.getInstance()//リアルタイムの時間を取得、タイマーを使うことで毎秒更新するため、毎秒動く
        val hour=calendar.get(Calendar.HOUR_OF_DAY)
        val minute=calendar.get(Calendar.MINUTE)
        val second=calendar.get(Calendar.SECOND)

        place?.text=time[count]["place"] as String
        if(count<data_size) {
            nextTime?.text = "${time[count + 1]["hour"]}時${time[count + 1]["minute"]}分"

        }//time[count+1]["place"] as String
        if(count==data_size){
            nextTime?.text=" "
        }
        if(count>0) {
            backTime?.text = "${time[count - 1]["hour"]}時${time[count - 1]["minute"]}分"
        }
        if(count==0){
            backTime?.text=" "
        }
        //busTime.text="${sethour}時${setminute}分"
        busTime?.text="${time[count]["hour"]}時${time[count]["minute"]}分"
      //  nowTime?.text = "${hour}時${minute}分${second}秒"

        if((limitminute >= 0 && limitminute <= 9) && (limitsecond >= 0 && limitsecond <= 9) ){
            limitTime?.text = "${limithour}:0${limitminute}:0${limitsecond}"

        }else if(limitsecond >= 0 && limitsecond <= 9){
            limitTime?.text = "${limithour}:${limitminute}:0${limitsecond}"

        }else if(limitminute >= 0 && limitminute <= 9){
            limitTime?.text = "${limithour}:0${limitminute}:${limitsecond}"

        }else {
            limitTime?.text = "${limithour}:${limitminute}:${limitsecond}"


        }







    }

    fun textTimeR(timeR: Array<MutableMap<String, Any>>,countR: Int,data_size: Int,limithourR: Int,limitminuteR: Int,limitsecondR: Int){
        if((limitminuteR >= 0 && limitminuteR <= 9) && (limitsecondR >= 0 && limitsecondR <= 9) ){

            val nextTimeR=view?.findViewById<TextView>(R.id.nextTimeR)
            val backTimeR=view?.findViewById<TextView>(R.id.backTimeR)
            val limitTimeR=view?.findViewById<TextView>(R.id.limitTimeR)
            val busTimeR=view?.findViewById<TextView>(R.id.busTimeR)
            val placeR=view?.findViewById<TextView>(R.id.placeR)

            limitTimeR?.text = "${limithourR}:0${limitminuteR}:0${limitsecondR}"

        }else if(limitsecondR >= 0 && limitsecondR <= 9){
            limitTimeR?.text = "${limithourR}:${limitminuteR}:0${limitsecondR}"

        }else if(limitminuteR >= 0 && limitminuteR <= 9){
            limitTimeR?.text = "${limithourR}:0${limitminuteR}:${limitsecondR}"

        }else {
            limitTimeR?.text = "${limithourR}:${limitminuteR}:${limitsecondR}"

        }




        placeR?.text=timeR[countR]["place"] as String
        if(countR<data_size) {
            nextTimeR?.text = "${timeR[countR + 1]["hour"]}時${timeR[countR + 1]["minute"]}分"
        }//time[count+1]["place"] as String
        if(countR==data_size){
            nextTimeR?.text = " "
        }

        if(countR>0) {
            backTimeR?.text = "${timeR[countR - 1]["hour"]}時${timeR[countR - 1]["minute"]}分"

        }
        if(countR==0){
            backTimeR?.text=" "
        }
        //busTime.text="${sethour}時${setminute}分"
        busTimeR?.text="${timeR[countR]["hour"]}時${timeR[countR]["minute"]}分"
    }

}