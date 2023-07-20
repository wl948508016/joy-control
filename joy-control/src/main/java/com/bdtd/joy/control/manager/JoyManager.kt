package com.bdtd.joy.control.manager

import com.bdtd.joy.control.R
import com.bdtd.joy.control.retrofit.RetrofitService
import com.future.components.client.appContext
import com.future.components.client.utils.LogUtils
import com.future.components.client.viewmodel.BaseViewModel
import com.future.components.net.NetworkHelper
import com.future.components.net.exception.NetException
import com.future.components.net.ext.request
import com.future.components.net.utils.RequestBodyUtils

/**
 *
 * @Description:    此类不暴露给外部
 * @Author:         future
 * @CreateDate:     2023/4/10 17:35
 */
internal object JoyManager {

    private var apiService = NetworkHelper.INSTANCE.getApi(RetrofitService::class.java, RetrofitService.getServerUrl())

    /**
     * 原子性确保多线程执行，也能拿到最新值
     */
    @Volatile
    private var single = false

    /**
     *
     * @Description:    创建跟随顺序信号(启动创建mode=shearer) && 创建自由运行顺序信号(启动创建mode=free_run)
     * @Author:         future
     * @CreateDate:     2023/4/13 15:31
     */
    fun shearerPrime(viewModel: BaseViewModel, pos: Int, dir: String, func: String, mode: String, actionSuccess: () -> Unit, actionError: () -> Unit = {}) {
        if(single) return
        val hashMap = HashMap<String, Any>()
        hashMap["support"] = pos
        hashMap["dir"] = dir
        hashMap["func"] = func
        hashMap["mode"] = mode
        val requestBody = RequestBodyUtils.createRequestBody(hashMap)
        viewModel.apply {
            apiService?.let {
                single = true
                postLoading(R.string.loading_create)
                request({ it.primes(requestBody) }, { netSuccess(this, R.string.create_success, actionSuccess) } ,
                    {
                        single = false
                        postLoadingCancel()
                        val res : Int =  when (it) {
                            -2 -> R.string.prs_out_rang
                            -1 -> R.string.invalid_parameter
                            2 -> R.string.gear_e_triggered
                            3 -> R.string.low_voltage_trip
                            4 -> R.string.same_initialization_exists
                            5 -> R.string.startup_memory_exhausted
                            6 -> R.string.no_shearer_found
                            7 -> R.string.provided_surface_range
                            8 -> R.string.shearer_not_in_range
                            9 -> R.string.shearer_has_no_direction
                            10 -> R.string.shearer_direction_error
                            11 -> R.string.radio_motion_tripped
                            1000 -> R.string.unable_to_parse_json
                            1001 -> R.string.location_not_specified
                            1002 -> R.string.startup_location_format_error
                            1003 -> R.string.startup_function_not_specified
                            1004 -> R.string.bad_startup_function
                            1005 -> R.string.no_starting_direction
                            1006 -> R.string.wrong_starting_direction
                            1007 -> R.string.startup_input_error
                            1008 -> R.string.startup_mode_error
                            1009 -> R.string.startup_prs_error
                            1010 -> R.string.prs_mode_not_specified
                            else -> R.string.unknown_error_code
                        }
                        postMessage(res)
                        actionError.invoke()
                    }, { netError(this, it, actionError) })
            }
        }
    }

    /**
     *
     * @Description:    激活顺序信号(启动自由运行模式)
     * @Author:         future
     * @CreateDate:     2023/4/13 15:32
     */
    fun initiate(viewModel: BaseViewModel, func: String, pos: Int, actionSuccess: () -> Unit, actionError: () -> Unit = {}) {
        if(single) return
        val hashMap = HashMap<String, Any>()
        hashMap["func"] = func
        hashMap["support"] = pos
        hashMap["initiated"] = true
        val requestBody = RequestBodyUtils.createRequestBody(hashMap)
        viewModel.apply {
            apiService?.let {
                single = true
                postLoading(R.string.loading_firing)
                request({ it.primes(requestBody) }, { netSuccess(this, R.string.firing_success, actionSuccess) }
                    ,{
                        single = false
                        postLoadingCancel()
                        val res : Int =  when (it) {
                            -3 -> R.string.not_started
                            -2 -> R.string.prs_out_rang
                            -1 -> R.string.invalid_parameter
                            1 -> R.string.success_initiated_startup
                            2 -> R.string.not_started_init_range
                            3 -> R.string.init_free_running_limit
                            4 -> R.string.startup_occurs_delay_time
                            5 -> R.string.early_push_will_occur
                            6 -> R.string.mining_head_time_will_occur
                            7 -> R.string.startup_cannot_run_already_run
                            8 -> R.string.start_pause
                            9 -> R.string.unable_start_due_interlocking
                            10 -> R.string.low_voltage_trip
                            11 -> R.string.emergency_stop_trip
                            12 -> R.string.not_allow_already_started
                            13 -> R.string.init_press_stop_start
                            14 -> R.string.previous_fault
                            15 -> R.string.startup_cannot_started
                            16 -> R.string.apih_not_updated
                            17 -> R.string.bracket_apih_not_updated
                            18 -> R.string.unable_mark_bracket_update
                            19 -> R.string.unable_stand_alone_slopes
                            20 -> R.string.lower_bracket_on_slope
                            21 -> R.string.started_second_advance_occur
                            1000 -> R.string.unable_parse_json_body
                            1001 -> R.string.initiate_error_1001
                            1002 -> R.string.initiate_error_1002
                            1003 -> R.string.startup_mode_error
                            1004 -> R.string.startup_input_error
                            else -> R.string.unknown_error_code
                        }
                        postMessage(res)
                        actionError.invoke()
                    }, { netError(this, it, actionError) })
            }
        }

    }

    /**
     *
     * @Description:    升级顺序信号
     * @Author:         future
     * @CreateDate:     2023/4/13 16:13
     */
    fun promote(viewModel: BaseViewModel, func: String, actionSuccess: () -> Unit, actionError: () -> Unit = {}) {
        if(single) return
        val hashMap = HashMap<String, Any>()
        hashMap["func"] = func
        hashMap["promote"] = true
        val requestBody = RequestBodyUtils.createRequestBody(hashMap)
        viewModel.apply {
            apiService?.let {
                single = true
                postLoading(appContext.getString(R.string.loading_promote))
                request({ it.promote(requestBody) }, { netSuccess(this, R.string.update_success, actionSuccess) },{
                    single = false
                    postLoadingCancel()
                    val res : Int = when (it) {
                        -3 -> R.string.not_started
                        1 -> R.string.success_upgraded
                        2 -> R.string.startup_not_upgraded
                        3 -> R.string.unable_locate_shearer_location
                        4 -> R.string.star_not_within_range
                        5 -> R.string.upgrade_duplicate_startup_type
                        6 -> R.string.not_starting_forward_direction
                        7 -> R.string.no_shearer_direction
                        8 -> R.string.shearer_start_direction_opposite
                        1000 -> R.string.unable_parse_request_json_body
                        1003 -> R.string.startup_function_not_specified
                        1004 -> R.string.bad_startup_function
                        else -> R.string.unknown_error_code
                    }
                    postMessage(res)
                    actionError.invoke()
                }, { netError(this, it, actionError) })
            }
        }
    }

    /**
     *
     * @Description:    暂停顺序信号 && 取消暂停顺序信号
     * @Author:         future
     * @CreateDate:     2023/4/13 16:17
     */
    fun pause(viewModel: BaseViewModel, pos: Int, func: String, mode: String, pause: Boolean?, actionSuccess: () -> Unit, actionError: () -> Unit = {}) {
        if(single) return
        val hashMap = HashMap<String, Any?>()
        hashMap["support"] = pos
        hashMap["func"] = func
        hashMap["mode"] = mode
        hashMap["pause"] = pause
        val requestBody = RequestBodyUtils.createRequestBody(hashMap)
        viewModel.apply {
            apiService?.let {
                single = true
                postLoading(appContext.getString(R.string.loading_execute))
                request({ it.primes(requestBody) }, { netSuccess(this, R.string.success_execute, actionSuccess) },{
                    single = false
                    postLoadingCancel()
                    val res : Int = when (it) {
                        -3 -> R.string.not_started
                        -1 -> R.string.startup_paused_or_resumed
                        1 -> R.string.successfully_paused
                        2 -> R.string.successfully_recovered
                        1000 -> R.string.unable_parse_request_json_body
                        1001 -> R.string.pause_error_1001
                        1002 -> R.string.pause_error_1002
                        1003 -> R.string.pause_error_1003
                        1004 -> R.string.pause_error_1004
                        1007 -> R.string.pause_error_1007
                        1008 -> R.string.pause_error_1008
                        1019 -> R.string.pause_error_1019
                        1020 -> R.string.pause_error_1020
                        else -> R.string.unknown_error_code
                    }
                    postMessage(res)
                    actionError.invoke()
                }, { netError(this, it, actionError) })
            }
        }

    }

    /**
     *
     * @Description:    删除顺序信号
     * @Author:         future
     * @CreateDate:     2023/4/13 16:24
     */
    fun delete(viewModel: BaseViewModel, pos: Int, func: String, mode: String, actionSuccess: () -> Unit, actionError: () -> Unit = {}) {
        if(single) return
        val hashMap = HashMap<String, Any>()
        hashMap["support"] = pos
        hashMap["func"] = func
        hashMap["mode"] = mode
        hashMap["del"] = true
        val requestBody = RequestBodyUtils.createRequestBody(hashMap)
        viewModel.apply {
            apiService?.let {
                single = true
                postLoading(appContext.getString(R.string.loading_delete))
                request({ it.primes(requestBody) }, { netSuccess(this, R.string.delete_success, actionSuccess) },
                    {
                        single = false
                        postLoadingCancel()
                        val res : Int = when (it) {
                            -3 -> R.string.not_started
                            1 -> R.string.deleted_startup
                            1000-> R.string.unable_parse_request_json_body
                            1001-> R.string.location_not_specified
                            1002-> R.string.startup_location_format_error
                            1003-> R.string.startup_function_not_specified
                            1004-> R.string.bad_startup_function
                            1007-> R.string.startup_input_error
                            1008-> R.string.startup_mode_error
                            else -> R.string.unknown_error_code
                        }
                        postMessage(res)
                        actionError.invoke()
                    }, { netError(this, it, actionError) })
            }
        }
    }

    /**
     *
     * @Description:    楔形点
     * @Author:         future
     * @CreateDate:     2023/4/13 16:53
     */
    fun wedge(viewModel: BaseViewModel, shieldNum: Int, targetTravel: Int, actionSuccess: () -> Unit, actionError: () -> Unit = {}) {
        if(single) return
        val hashMap = HashMap<String, Any>()
        hashMap["support"] = shieldNum
        hashMap["target"] = targetTravel
        val requestBody = RequestBodyUtils.createRequestBody(hashMap)
        viewModel.apply {
            apiService?.let {
                single = true
                postLoading(appContext.getString(R.string.loading_create))
                request({ it.wedge(requestBody) }, { netSuccess(this, R.string.create_success, actionSuccess) } ,
                    {
                        single = false
                        postLoadingCancel()
                        val res : Int = when (it) {
                            -2 -> R.string.create_wedge_error_fu_2
                            1 -> R.string.number_not_within_range
                            2 -> R.string.input_is_not_from_0_to_100
                            3 -> R.string.not_allow_tag_correction
                            4 -> R.string.tag_activation
                            5 -> R.string.wedge_point_already_exist
                            6 -> R.string.input_shield_inside_existing_wedge
                            1000 -> R.string.unable_parse_request_json_body
                            1023 -> R.string.no_support_parameter
                            1024 -> R.string.support_parameter_format_incorrect
                            1025 -> R.string.create_wedge_error_1025
                            1026 -> R.string.create_wedge_error_1026
                            else -> R.string.unknown_error_code
                        }
                        postMessage(res)
                        actionError.invoke()
                    }, { netError(this, it, actionError) })
            }
        }
    }

    /**
     *
     * @Description:    删除所有楔形点
     * @Author:         future
     * @CreateDate:     2023/4/13 16:53
     */
    fun deleteAllWedge(viewModel: BaseViewModel, actionSuccess: () -> Unit, actionError: () -> Unit = {}) {
        if(single) return
        val hashMap = HashMap<String, Any>()
        hashMap["del"] = true
        val requestBody = RequestBodyUtils.createRequestBody(hashMap)
        viewModel.apply {
            apiService?.let {
                single = true
                postLoading(appContext.getString(R.string.loading_delete))
                request({ it.wedge(requestBody) }, { netSuccess(this, R.string.delete_success, actionSuccess) },
                    {
                        single = false
                        postLoadingCancel()
                        val res : Int = when (it) {
                            -1 -> R.string.delete_wedge_error_fu_1
                            1 -> R.string.no_wedge_can_be_delete
                            1000 -> R.string.unable_parse_request_json_body
                            else -> R.string.unknown_error_code
                        }
                        postMessage(res)
                        actionError.invoke()
                    }, { netError(this, it, actionError) })
            }
        }
    }

    /**
     *
     * @Description:    标记支架  && 取消标记支架
     * @Author:         future
     * @CreateDate:     2023/4/13 16:58
     */
    fun markOrCancelShield(viewModel: BaseViewModel, shieldNum: Int, mark: Boolean, actionSuccess: () -> Unit, actionError: () -> Unit = {}) {
        if(single) return
        val hashMap = HashMap<String, Any>()
        hashMap["mark"] = mark
        hashMap["support"] = shieldNum
        val requestBody = RequestBodyUtils.createRequestBody(hashMap)
        viewModel.apply {
            apiService?.let {
                single = true
                postLoading(if (mark) R.string.loading_mark else R.string.loading_mark_cancel)
                request({ it.markOrCancelShield(requestBody) }, { netSuccess(this, if (mark) R.string.success_mark else R.string.success_mark_cancel, actionSuccess) },
                    {
                        single = false
                        postLoadingCancel()
                        val res : Int = when (it) {
                            -2 -> R.string.mark_or_cancel_error_fu_2
                            -1 -> R.string.invalid_parameter
                            1 -> R.string.mask_mark
                            2 -> R.string.mask_not_mark
                            3 -> R.string.block_num_not_within_range
                            1000 -> R.string.unable_parse_request_json_body
                            1009 -> R.string.no_support_parameter
                            1010 -> R.string.support_parameter_format_incorrect
                            1027 -> R.string.mark_or_cancel_error_1027
                            1028 -> R.string.mark_or_cancel_error_1028
                            else -> R.string.unknown_error_code
                        }
                        postMessage(res)
                        actionError.invoke()
                    }, { netError(this, it, actionError) })
            }
        }

    }

    /**
     *
     * @Description:
     * @Author:         future
     * @CreateDate:     2023/4/13 17:09
     */
    fun clientOTP(viewModel: BaseViewModel, pwd: String, actionSuccess: () -> Unit, actionError: (Int) -> Unit = {}) {
        if(single) return
        val hashMap = HashMap<String, Any>()
        hashMap["password"] = pwd
        val requestBody = RequestBodyUtils.createRequestBody(hashMap)
        viewModel.apply {
            apiService?.let {
                single = true
                postLoading(R.string.loading_verify)
                request({ it.checkOTP(requestBody) }, { netSuccess(viewModel,R.string.verify_success,actionSuccess) },
                    {
                        single = false
                        postLoadingCancel()
                        val res : Int = when (it) {
                            2 -> R.string.otp_error_2
                            3 -> R.string.otp_error_3
                            else -> R.string.unknown_error_code
                        }
                        actionError.invoke(res)
                    },{ netError(this, it) {} })
            }
        }

    }

    /**
     *
     * @Description:    释放压力
     * @Author:         future
     * @CreateDate:     2023/4/13 17:09
     */
    fun releasePush(viewModel: BaseViewModel, actionSuccess: () -> Unit, actionError: () -> Unit = {}) {
        if(single) return
        val hashMap = HashMap<String, Any>()
        hashMap["gate"] = "mg"
        val requestBody = RequestBodyUtils.createRequestBody(hashMap)
        viewModel.apply {
            apiService?.let {
                single = true
                postLoading(R.string.loading_release)
                request({ it.releasePush(requestBody) }, { netSuccess(this, R.string.release_success, actionSuccess) },
                    {
                        single = false
                        postLoadingCancel()
                        val res : Int = when (it) {
                            -3 -> R.string.release_push_error_fu_3
                            1 -> R.string.success
                            2 -> R.string.release_push_error_2
                            3 -> R.string.release_push_error_3
                            21 -> R.string.release_push_error_21
                            1000 -> R.string.unable_parse_request_json_body
                            1021 -> R.string.release_push_error_1021
                            1022 -> R.string.release_push_error_1022
                            else -> R.string.unknown_error_code
                        }
                        postMessage(res)
                        actionError.invoke()
                    }, { netError(this, it,actionError) })
            }
        }

    }

    /**
     *
     * @Description:    取消端头自动化
     * @Author:         future
     * @CreateDate:     2023/4/21 10:44
     */
    fun automationCancel(viewModel: BaseViewModel, actionSuccess: () -> Unit, actionError: () -> Unit = {}) {
        if(single) return
        val hashMap = HashMap<String, Any>()
        hashMap["gate"] = "mg"
        val requestBody = RequestBodyUtils.createRequestBody(hashMap)
        viewModel.apply {
            apiService?.let {
                single = true
                postLoading(R.string.loading_release)
                request({ it.automationCancel(requestBody) }, { netSuccess(this, R.string.release_success, actionSuccess) } ,
                    {
                        single = false
                        postLoadingCancel()
                        val res = when(it){
                            -1-> R.string.automation_not_currently_active
                            1-> R.string.cancelled_automation
                            1000-> R.string.unable_parse_request_json_body
                            1021-> R.string.automation_cancel_error_1021
                            1022-> R.string.automation_cancel_error_1022
                            else -> R.string.unknown_error_code
                        }
                        postMessage(res)
                        actionError.invoke() }, { netError(this, it,actionError) })
            }
        }

    }

    /**
     *
     * @Description:    互帮板自动参数
     * @Author:         future
     * @CreateDate:     2023/4/21 10:44
     */
    fun autoParam(viewModel: BaseViewModel, auto: String, actionSuccess: () -> Unit, actionError: () -> Unit = {}) {
        if(single) return
        val hashMap = HashMap<String, Any>()
        hashMap["auto"] = auto
        val requestBody = RequestBodyUtils.createRequestBody(hashMap)
        viewModel.apply {
            apiService?.let {
                single = true
                postLoading(R.string.loading_execute)
                request({ it.autoParam(requestBody) }, { netSuccess(this, R.string.success_execute, actionSuccess) },
                    {
                        single = false
                        postLoadingCancel()
                        val res = when(it){
                            1-> R.string.success
                            1000-> R.string.unable_parse_request_json_body
                            1076-> R.string.auto_param_error_1076
                            1077-> R.string.auto_param_error_1077
                            else -> R.string.unknown_error_code
                        }
                        postMessage(res)
                        actionError.invoke()
                    }, { netError(this, it,actionError) })
            }
        }

    }

    /**
     *
     * @Description:    空顶区域设置
     * @Author:         future
     * @CreateDate:     2023/4/21 10:44
     */
    fun cavityRegion(viewModel: BaseViewModel, startShield: Int, endShield: Int, coalCuttingNum: Int, actionSuccess: () -> Unit, actionError: () -> Unit = {}) {
        if(single) return
        val hashMap = HashMap<String, Any>()
        hashMap["firstsupport"] = startShield
        hashMap["lastsupport"] = endShield
        hashMap["passes"] = coalCuttingNum
        val requestBody = RequestBodyUtils.createRequestBody(hashMap)
        viewModel.apply {
            apiService?.let {
                single = true
                postLoading(R.string.loading_setting)
                request({ it.cavityRegion(requestBody) }, { netSuccess(this, R.string.success_set, actionSuccess) },
                    {
                        single = false
                        postLoadingCancel()
                        val res = when(it){
                            1-> R.string.success
                            1000-> R.string.unable_parse_request_json_body
                            1078-> R.string.not_have_first_param
                            1079-> R.string.first_param_format_error
                            1080-> R.string.not_have_end_param
                            1081-> R.string.end_param_format_error
                            1082-> R.string.cavity_region_error_1082
                            1083-> R.string.cavity_region_error_1083
                            else -> R.string.unknown_error_code
                        }
                        postMessage(res)
                        actionError.invoke()
                    }, { netError(this, it,actionError) })
            }
        }
    }

    /**
     * @Description:    PRS控制话路
     * @Author:         future
     * @param           startSupport 开始支架
     * @param           endSupport 结束支架
     * @param           switch true 开始 false 结束
     * @CreateDate:     2023/5/11 10:44
     */
    fun prsSession(viewModel: BaseViewModel, startSupport: Int, endSupport: Int, switch: Boolean, actionSuccess: () -> Unit, actionError: () -> Unit = {}) {
        if(single) return
        val hashMap = HashMap<String, Any>()
        hashMap["startsupport"] = startSupport
        hashMap["endsupport"] = endSupport
        hashMap["start"] = switch
        val requestBody = RequestBodyUtils.createRequestBody(hashMap)
        viewModel.apply {
            apiService?.let {
                single = true
                postLoading(if (switch) R.string.loading_prs_start else R.string.loading_prs_end)
                request({ it.prsSession(requestBody) }, { netSuccess(this, if (switch) R.string.prs_start_success else R.string.prs_end_success, actionSuccess) },
                    {
                        single = false
                        postLoadingCancel()
                        val res = when(it){
                            -5-> R.string.param_not_use
                            -2-> R.string.request_support_not_rps_range
                            -1-> R.string.param_not_allow_range
                            1-> R.string.prs_session_error_1
                            2-> R.string.prs_session_error_2
                            3-> R.string.prs_session_error_3
                            4-> R.string.prs_session_error_4
                            5-> R.string.prs_session_error_5
                            6-> R.string.prs_session_error_6
                            7-> R.string.prs_session_error_7
                            8-> R.string.prs_session_error_8
                            9-> R.string.prs_session_error_9
                            10-> R.string.prs_session_error_10
                            11-> R.string.prs_session_error_11
                            12-> R.string.prs_session_error_12
                            13-> R.string.prs_session_error_13
                            14-> R.string.prs_session_error_14
                            1000-> R.string.unable_parse_request_json_body
                            1044-> R.string.not_have_first_param
                            1045-> R.string.first_param_format_error
                            1046-> R.string.not_have_end_param
                            1047-> R.string.end_param_format_error
                            1048-> R.string.prs_session_error_1048
                            1049-> R.string.prs_session_error_1049
                            else -> R.string.unknown_error_code
                        }
                        postMessage(res)
                        actionError.invoke()
                    }, { netError(this, it,actionError)  })
            }
        }
    }

    /**
     * @Description:    覆盖
     * @Author:         future
     * @param           func 单控|模板|成组
     * @param           error 错误码
     * @CreateDate:     2023/5/11 10:44
     */
    fun override(viewModel: BaseViewModel, func: String, error: Int, actionSuccess: (override: Int) -> Unit, actionError: () -> Unit = {}) {
        if(single) return
        val hashMap = HashMap<String, Any>()
        hashMap["func"] = func
        hashMap["error"] = error
        val requestBody = RequestBodyUtils.createRequestBody(hashMap)
        viewModel.apply {
            apiService?.let {
                postLoading(R.string.loading_override)
                single = true
                request({ it.override(requestBody) }, {
                    postLoadingCancel()
                    postMessage(R.string.override_success)
                    actionSuccess.invoke(it.overridecode)
                    single = false
                },{
                    single = false
                    postLoadingCancel()
                    val res = when(it){
                        1-> R.string.success
                        2-> R.string.override_error_2
                        1000-> R.string.unable_parse_request_json_body
                        1072-> R.string.error_1072
                        1073-> R.string.error_1073
                        1074-> R.string.error_1074
                        1075-> R.string.error_1075
                        else -> R.string.unknown_error_code
                    }
                    postMessage(res)
                    actionError.invoke()
                }, { netError(this, it) {} })
            }
        }
    }

    /**
     * @Description:    PRS电磁阀控制
     * @Author:         future
     * @param           support 支架号
     * @param           solenoid 电磁阀ID
     * @param           timeout 持续时间
     * @param           ovrride 覆盖码
     * @CreateDate:     2023/5/11 10:44
     */
    fun prsControl(viewModel: BaseViewModel, support: Int, solenoid: String, ovrride: Int, actionSuccess: () -> Unit, actionError: (Int) -> Unit = {}) {
        if(single) return
        val hashMap = HashMap<String, Any>()
        hashMap["support"] = support
        hashMap["solenoid"] = solenoid
        hashMap["timeout"] = 1000
        hashMap["ovrride"] = ovrride
        val requestBody = RequestBodyUtils.createRequestBody(hashMap)
        viewModel.apply {
            apiService?.let {
                single = true
                postLoading(R.string.loading_control)
                request({ it.prsControl(requestBody) }, { netSuccess(this, R.string.control_success, actionSuccess) },
                    {
                        single = false
                        postLoadingCancel()
                        val res : Int = when (it) {
                            -5 -> R.string.prs_control_error_fu_5
                            -2 -> R.string.prs_control_error_fu_2
                            1 -> R.string.success
                            2 -> R.string.prs_control_error_2
                            3 -> R.string.prs_control_error_3
                            4 -> R.string.prs_control_error_4
                            5 -> R.string.prs_control_error_5
                            6 -> R.string.prs_control_error_6
                            7 -> R.string.prs_control_error_7
                            9 -> R.string.prs_control_error_9
                            10 -> R.string.prs_control_error_10
                            11 -> R.string.prs_control_error_11
                            12 -> R.string.prs_control_error_12
                            13 -> R.string.prs_control_error_13
                            14 -> R.string.prs_control_error_14
                            15 -> R.string.prs_control_error_15
                            1000 -> R.string.unable_parse_request_json_body
                            1009 -> R.string.no_support_parameter
                            1010 -> R.string.support_parameter_format_incorrect
                            1052 -> R.string.error_1052
                            1053 -> R.string.error_1053
                            1054 -> R.string.error_1054
                            1055 -> R.string.error_1055
                            1056 -> R.string.error_1056
                            1057 -> R.string.error_1057
                            else -> R.string.unknown_error_code
                        }
                        postMessage(res)
                        actionError.invoke(it)
                    }, { netError(this, it){} })
            }
        }
    }

    /**
     * @Description:    PRS电磁阀模块控制
     * @Author:         future
     * @param           support 支架号
     * @param           solenoid 电磁阀ID
     * @param           ovrride 覆盖码
     * @CreateDate:     2023/5/19 10:44
     */
    fun prsControlTemplate(viewModel: BaseViewModel, support: Int, template: String, ovrride: Int, actionSuccess: () -> Unit, actionError: (Int) -> Unit = {}) {
        if(single) return
        val hashMap = HashMap<String, Any>()
        hashMap["support"] = support
        hashMap["template"] = template
        hashMap["ovrride"] = ovrride
        val requestBody = RequestBodyUtils.createRequestBody(hashMap)
        viewModel.apply {
            apiService?.let {
                single = true
                postLoading(R.string.loading_control)
                request({ it.prsControlTemplate(requestBody) }, { netSuccess(this, R.string.control_success, actionSuccess) },
                    {
                        single = false
                        postLoadingCancel()
                        val res : Int = when (it) {
                            -5 -> R.string.prs_control_error_fu_5
                            -2 -> R.string.prs_control_error_fu_2
                            1 -> R.string.success
                            2 -> R.string.prs_control_error_2
                            3 -> R.string.prs_control_error_3
                            4 -> R.string.prs_control_error_4
                            5 -> R.string.prs_control_template_error_5
                            6 -> R.string.prs_control_template_error_6
                            7 -> R.string.prs_control_error_12
                            8 -> R.string.prs_control_error_13
                            9 -> R.string.prs_control_error_15
                            1000 -> R.string.unable_parse_request_json_body
                            1009 -> R.string.no_support_parameter
                            1010 -> R.string.support_parameter_format_incorrect
                            1050 -> R.string.error_1050
                            1051 -> R.string.error_1051
                            1056 -> R.string.error_1056
                            1059 -> R.string.error_1057
                            else -> R.string.unknown_error_code
                        }
                        postMessage(res)
                        actionError.invoke(it)
                    }, { netError(this, it){} })
            }
        }
    }


    /**
     * @Description:    PRS电磁阀成组控制
     * @Author:         future
     * @param           support 支架号
     * @param           solenoid 电磁阀ID
     * @param           timeout 持续时间
     * @param           ovrride 覆盖码
     * @CreateDate:     2023/5/11 10:44
     */
    fun prsBankControl(viewModel: BaseViewModel, support: Int, solenoid: String, ovrride: Int, actionSuccess: () -> Unit, actionError: (Int) -> Unit = {}) {
        if(single) return
        val hashMap = HashMap<String, Any>()
        hashMap["support"] = support
        hashMap["solenoid"] = solenoid
        hashMap["timeout"] = 1000
        hashMap["ovrride"] = ovrride
        val requestBody = RequestBodyUtils.createRequestBody(hashMap)
        viewModel.apply {
            apiService?.let {
                single = true
                postLoading(R.string.loading_control)
                request({ it.prsBankControl(requestBody) }, { netSuccess(this, R.string.control_success, actionSuccess) },
                    {
                        single = false
                        postLoadingCancel()
                        val res : Int = when (it) {
                            -5 -> R.string.prs_control_error_fu_5
                            -2 -> R.string.prs_control_error_fu_2
                            1 -> R.string.success
                            2 -> R.string.prs_control_error_2
                            3 -> R.string.prs_control_error_3
                            4 -> R.string.prs_control_error_4
                            5 -> R.string.prs_control_error_5
                            6 -> R.string.prs_control_error_6
                            7 -> R.string.prs_control_error_7
                            9 -> R.string.prs_control_error_9
                            10 -> R.string.prs_control_error_10
                            11 -> R.string.prs_control_error_11
                            12 -> R.string.prs_control_error_12
                            13 -> R.string.prs_control_error_13
                            14 -> R.string.prs_control_error_14
                            15 -> R.string.prs_control_error_15
                            1000 -> R.string.unable_parse_request_json_body
                            1009 -> R.string.no_support_parameter
                            1010 -> R.string.support_parameter_format_incorrect
                            1052 -> R.string.error_1052
                            1053 -> R.string.error_1053
                            1054 -> R.string.error_1054
                            1055 -> R.string.error_1055
                            1056 -> R.string.error_1056
                            1059 -> R.string.error_1057
                            else -> R.string.unknown_error_code
                        }
                        postMessage(res)
                        actionError.invoke(it)
                    }, { netError(this, it) {} })
            }
        }
    }

    /**
     * @Description:    清除拉架故障
     * @Author:         future
     * @param           support 支架号
     * @CreateDate:     2023/5/11 10:44
     */
    fun clearAdvFault(viewModel: BaseViewModel, support: Int, actionSuccess: () -> Unit, actionError: () -> Unit = {}) {
        if(single) return
        val hashMap = HashMap<String, Any>()
        hashMap["support"] = support
        val requestBody = RequestBodyUtils.createRequestBody(hashMap)
        viewModel.apply {
            apiService?.let {
                single = true
                postLoading(R.string.loading_control)
                request({ it.clearAdvFault(requestBody) }, { netSuccess(this, R.string.control_success, actionSuccess) },
                    {
                        single = false
                        postLoadingCancel()
                        val res = when(it){
                            -2-> R.string.clear_adv_fault_error_fu_2
                            1-> R.string.clear_adv_fault_error_1
                            1000-> R.string.unable_parse_request_json_body
                            1009-> R.string.no_support_parameter
                            1010-> R.string.support_parameter_format_incorrect
                            else -> R.string.unknown_error_code
                        }
                        postMessage(res)
                        actionError.invoke() }
                    , { netError(this, it, actionError) })
            }
        }
    }

    /**
     * @Description:    取消自动调直
     * @Author:         future
     * @CreateDate:     2023/5/11 10:44
     */
    fun canCellAndMark(viewModel: BaseViewModel, actionSuccess: () -> Unit, actionError: () -> Unit = {}) {
        if(single) return
        val hashMap = HashMap<String, Any>()
        val requestBody = RequestBodyUtils.createRequestBody(hashMap)
        viewModel.apply {
            apiService?.let {
                single = true
                postLoading(R.string.loading_control)
                request({ it.canCellAndMark(requestBody) }, { netSuccess(this, R.string.control_success, actionSuccess) },
                    {
                        postLoadingCancel()
                        single = false
                    }, { netError(this, it, actionError) })
            }
        }
    }

    /**
      *
      * @Description:    更新CCU地址
      * @Author:         future
      * @CreateDate:     2023/5/29 15:17
     */
    fun updateApiService(){
        apiService = NetworkHelper.INSTANCE.getApi(RetrofitService::class.java, RetrofitService.getServerUrl())
    }

    /**
     *
     * @Description:    网络请求失败通用处理
     * @Author:         future
     * @CreateDate:     2023/4/13 15:32
     */
    private fun netError(viewModel: BaseViewModel, ex: NetException, actionError: () -> Unit) {
        viewModel.apply {
            single = false
            postLoadingCancel()
            postMessage(R.string.net_error)
            LogUtils.e("NetException: $ex")
            actionError.invoke()
        }
    }

    /**
     *
     * @Description:    网络请求成功通用处理
     * @Author:         future
     * @CreateDate:     2023/4/13 16:44
     */
    private fun netSuccess(viewModel: BaseViewModel, res: Int, actionSuccess: () -> Unit) {
        viewModel.apply {
            single = false
            postLoadingCancel()
            postMessage(res)
            actionSuccess.invoke()
        }
    }
}