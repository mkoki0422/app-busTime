package com.example.bustime

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import kotlinx.android.synthetic.main.activity_main.*
//git test
class MainActivity : AppCompatActivity() {


    class  Myadapter(fm: FragmentManager, private val fragmentList: MainActivity):
        FragmentPagerAdapter(fm,
        BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){


        override fun getItem(position: Int): Fragment {
            when(position){
                0-> {return toukou()}
                else->{return gekou()}
            }
            // fragmentList[position]
        }

        override fun getCount(): Int {
            return 2
        }

        override fun getPageTitle(position: Int): CharSequence? {
            when(position){
                0->{return "立命館大学行き"}
                else->{return "南草津駅行き"}
            }

        }


    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)






        val adapter=Myadapter(supportFragmentManager,this)
        pager.adapter=adapter
        tabs.setupWithViewPager(pager)




    }




}


