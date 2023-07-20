package com.bdtd.joy.control.retrofit

import android.text.TextUtils
import com.bdtd.joy.control.model.Constants
import com.bdtd.joy.control.model.OverrideModel
import com.bdtd.joy.control.utils.getString
import com.future.components.net.model.BaseResponse
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface RetrofitService {
    /**
     * @Headers({"Content-type:application/json;charset=UTF-8"})
     * 1. JSON  格式 请求体
     * RequestBody body = RequestBody.create(JSON, "json格式的字符串");
     *
     * 2.  文件上传
     * RequestBody requestBody = new MultipartBody.Builder()
     * .setType(MultipartBody.FORM)
     * .addFormDataPart("file", file.getName(), RequestBody.create(PNG, file))
     * .build();
     * 3. 表单
     * FormBody body = new FormBody.Builder()
     * .add("limit", String.valueOf(LIMIT))
     * .add("page", String.valueOf(pageValue))
     * .build();
     */
    companion object {
        var SERVER_URL_TEST = "https://10.40.138.157:11111" // 测试服务器地址
        var SERVER_URL = "https://10.40.138.157:11111" // 正式服务器地址

        fun getServerUrl(): String {
            val url = getString(Constants.URL_TAG)
            if (!TextUtils.isEmpty(url)) {
                SERVER_URL = url
            }
            return SERVER_URL
        }
    }

    /**
     * 一次性密码
     */
    @Headers("Content-Type:application/json", "charset:UTF-8")
    @POST("/clientotp")
    suspend fun checkOTP(@Body requestBody: RequestBody): BaseResponse<Any>

    /**
     * 启动创建、启动自由运行模式、启动暂停、删除顺序信号
     */
    @Headers("Content-Type:application/json", "charset:UTF-8")
    @POST("/primes")
    suspend fun primes(@Body requestBody: RequestBody): BaseResponse<Any>

    /**
     * 启动升级
     */
    @Headers("Content-Type:application/json", "charset:UTF-8")
    @POST("/primes")
    suspend fun promote(@Body requestBody: RequestBody): BaseResponse<Any>

    /**
     * 创建楔形点 && 删除契形点
     */
    @Headers("Content-Type:application/json", "charset:UTF-8")
    @POST("/wedge")
    suspend fun wedge(@Body requestBody: RequestBody): BaseResponse<Any>

    /**
     * 标记支架  && 取消标记支架
     */
    @Headers("Content-Type:application/json", "charset:UTF-8")
    @POST("/mark")
    suspend fun markOrCancelShield(@Body requestBody: RequestBody):BaseResponse<Any>

    /**
     * 释放压力
     */
    @Headers("Content-Type:application/json", "charset:UTF-8")
    @POST("/releasepush")
    suspend fun releasePush(@Body requestBody: RequestBody): BaseResponse<Any>

    /**
     * 取消端头自动化
     */
    @Headers("Content-Type:application/json", "charset:UTF-8")
    @POST("/automationcancel")
    suspend fun automationCancel(@Body requestBody: RequestBody): BaseResponse<Any>

    /**
     * 单控/成组 控制话路 开始/结束
     */
    @Headers("Content-Type:application/json", "charset:UTF-8")
    @POST("/prssession")
    suspend fun prsSession(@Body requestBody: RequestBody): BaseResponse<Any>

    /**
     * 覆盖
     */
    @Headers("Content-Type:application/json", "charset:UTF-8")
    @POST("/override")
    suspend fun override(@Body requestBody: RequestBody): BaseResponse<OverrideModel>

    /**
     * PRS电磁阀控制
     */
    @Headers("Content-Type:application/json", "charset:UTF-8")
    @POST("/prscontrol")
    suspend fun prsControl(@Body requestBody: RequestBody): BaseResponse<Any>

    /**
     * PRS电磁阀模板控制
     */
    @Headers("Content-Type:application/json", "charset:UTF-8")
    @POST("/prscontroltemplate")
    suspend fun prsControlTemplate(@Body requestBody: RequestBody): BaseResponse<Any>

    /**
     * PRS电磁阀成组控制
     */
    @Headers("Content-Type:application/json", "charset:UTF-8")
    @POST("/prsbankcontrol")
    suspend fun prsBankControl(@Body requestBody: RequestBody): BaseResponse<Any>

    /**
     * 互帮板自动参数
     */
    @Headers("Content-Type:application/json", "charset:UTF-8")
    @POST("/autospragparam")
    suspend fun autoParam(@Body requestBody: RequestBody): BaseResponse<Any>

    /**
     * 空顶区域设置
     */
    @Headers("Content-Type:application/json", "charset:UTF-8")
    @POST("/cavityregionset")
    suspend fun cavityRegion(@Body requestBody: RequestBody): BaseResponse<Any>

    /**
     * 清除拉架故障
     */
    @Headers("Content-Type:application/json", "charset:UTF-8")
    @POST("/clearadvfault")
    suspend fun clearAdvFault(@Body requestBody: RequestBody): BaseResponse<Any>

    /**
     * 取消自动调直
     */
    @Headers("Content-Type:application/json", "charset:UTF-8")
    @POST("/cancellandmark")
    suspend fun canCellAndMark(@Body requestBody: RequestBody): BaseResponse<Any>

}