package com.example.sharedpreferences.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.edit
import androidx.preference.PreferenceManager
import com.example.sharedpreferences.R
import com.example.sharedpreferences.databinding.FragmentSharedBinding

class SharedFragment : Fragment() {
    private var _binding : FragmentSharedBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSharedBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pref = PreferenceManager.getDefaultSharedPreferences(context)
        //プリファレンスから、保存されている文字列を取得
        //まだ保存されていない場合は”未登録”を返す
        val storedText = pref.getString("key","未登録")
        binding.edShared.setText(storedText)
        //ボタンがタップされたときの処理
        binding.button.setOnClickListener{
            //テキストボックスに入力されている文字列
            val inputText = binding.edShared.text.toString()
            //プリファレンスに保存する
            pref.edit{
                //deviceFileExproler→data→data→com.example.sharedpreferences→shared_prefs
                //com.example.sharedpreferences_preferences.xmlとして保存されている
                putString("key",inputText)
            }
        }
    }

    override fun onDestroy(){
        super.onDestroy()
        _binding = null
    }
}