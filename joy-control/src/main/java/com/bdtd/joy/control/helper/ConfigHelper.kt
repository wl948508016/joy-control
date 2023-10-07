package com.bdtd.joy.control.helper

import com.bdtd.joy.control.model.Constants.BASE_TIMEOUT
import com.bdtd.joy.control.model.Constants.COLUMN_TIMEOUT
import com.bdtd.joy.control.model.Constants.DEFAULT_TIMEOUT
import com.bdtd.joy.control.model.Constants.PUSH_TIMEOUT
import com.bdtd.joy.control.model.Constants.SIDE_SHIELD_TIMEOUT
import com.bdtd.joy.control.model.Constants.SPRAG_TIMEOUT
import com.bdtd.joy.control.utils.getString
import com.bdtd.joy.control.utils.pushString

/**
 *
 * @Description:
 * @Author:         future
 * @CreateDate:     2023/9/8 14:54
 */
object ConfigHelper {

    /**
     * 更新整体执行时长
     * @param default 默认执行时间
     * @param sprag 护帮板
     * @param sideShield 侧护板
     * @param column 立柱
     * @param base 底座
     * @param push 推溜
     */
    fun updateConfigTimeout(default:String, sprag:String, sideShield:String, column:String, base:String, push:String){
        updateDefaultTimeout(default)
        updateSpragTimeout(sprag)
        updateSideShieldTimeout(sideShield)
        updateColumnTimeout(column)
        updateBaseTimeout(base)
        updatePushTimeout(push)
    }

    /**
     * 更新默认执行时长
     * @param string 时长
     */
    private fun updateDefaultTimeout(string: String){
        if(string.isEmpty() || string == "0") return
        pushString(DEFAULT_TIMEOUT,string)
    }

    /**
     * 获取默认执行时长
     */
    fun getDefaultTimeout():String{
        return getString(DEFAULT_TIMEOUT).ifEmpty { "1" }
    }

    /**
     * 更新护帮板执行时长
     * @param string 时长
     */
    private fun updateSpragTimeout(string: String){
        if(string.isEmpty() || string == "0") return
        pushString(SPRAG_TIMEOUT,string)
    }

    /**
     * 获取护帮板执行时长
     */
    fun getSpragTimeout():String{
        return getString(SPRAG_TIMEOUT).ifEmpty { getDefaultTimeout() }
    }

    /**
     * 更新侧护板执行时长
     * @param string 时长
     */
    private fun updateSideShieldTimeout(string: String){
        if(string.isEmpty() || string == "0") return
        pushString(SIDE_SHIELD_TIMEOUT,string)
    }

    /**
     * 获取侧护板执行时长
     */
    fun getSideShieldTimeout():String{
        return getString(SIDE_SHIELD_TIMEOUT).ifEmpty { getDefaultTimeout() }
    }

    /**
     * 更新立柱执行时长
     * @param string 时长
     */
    private fun updateColumnTimeout(string: String){
        if(string.isEmpty() || string == "0") return
        pushString(COLUMN_TIMEOUT,string)
    }

    /**
     * 获取立柱执行时长
     */
    fun getColumnTimeout():String{
        return getString(COLUMN_TIMEOUT).ifEmpty { getDefaultTimeout() }
    }

    /**
     * 更新底座执行时长
     * @param string 时长
     */
    private fun updateBaseTimeout(string: String){
        if(string.isEmpty() || string == "0") return
        pushString(BASE_TIMEOUT,string)
    }

    /**
     * 获取底座执行时长
     */
    fun getBaseTimeout():String{
        return getString(BASE_TIMEOUT).ifEmpty { getDefaultTimeout() }
    }

    /**
     * 更新推溜执行时长
     * @param string 时长
     */
    private fun updatePushTimeout(string: String){
        if(string.isEmpty() || string == "0") return
        pushString(PUSH_TIMEOUT,string)
    }

    /**
     * 获取推溜执行时长
     */
    fun getPushTimeout():String{
        return getString(PUSH_TIMEOUT).ifEmpty { getDefaultTimeout() }
    }
}