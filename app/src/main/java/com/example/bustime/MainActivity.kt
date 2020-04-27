package com.example.bustime

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import kotlinx.android.synthetic.main.activity_main.*
//git test
class MainActivity : AppCompatActivity() {


    class  Myadapter(fm: FragmentManager,private  val fragmentList: List<Fragment>):FragmentStatePagerAdapter(fm,
        BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){


        override fun getItem(position: Int): Fragment {
            return  fragmentList[position]
        }

        override fun getCount(): Int {
            return fragmentList.size
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val fragmentList= arrayListOf<Fragment>(
            toukou(),
            gekou()

        )

        val adapter=Myadapter(supportFragmentManager,fragmentList)
        pager.adapter=adapter

        gekou.setOnClickListener{
            pager.currentItem+=1
        }
        toukou.setOnClickListener{
            pager.currentItem-=1
        }

    }



}


