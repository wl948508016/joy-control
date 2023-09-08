package com.bdtd.joy.control.helper

import com.bdtd.joy.control.manager.JoyManager
import com.bdtd.joy.control.model.Constants.GROUP_SIDE_SHIELD_EXT
import com.bdtd.joy.control.model.Constants.GROUP_SIDE_SHIELD_RET
import com.bdtd.joy.control.model.Constants.GROUP_SOLENOID_ADVANCE
import com.bdtd.joy.control.model.Constants.GROUP_SOLENOID_PUSH
import com.bdtd.joy.control.model.Constants.GROUP_SOLENOID_SET
import com.bdtd.joy.control.model.Constants.GROUP_SOLENOID_SPRAG_EXT
import com.bdtd.joy.control.model.Constants.GROUP_SOLENOID_SPRAG_RET
import com.bdtd.joy.control.model.Constants.GROUP_SOLENOID_WATER_CURTAIN
import com.bdtd.joy.control.model.Constants.SINGLE_SIDE_SHIELD_EXT
import com.bdtd.joy.control.model.Constants.SINGLE_SIDE_SHIELD_RET
import com.bdtd.joy.control.model.Constants.SINGLE_SOLENOID_ADVANCE
import com.bdtd.joy.control.model.Constants.SINGLE_SOLENOID_BASE_LIFT
import com.bdtd.joy.control.model.Constants.SINGLE_SOLENOID_LOWER
import com.bdtd.joy.control.model.Constants.SINGLE_SOLENOID_PUSH
import com.bdtd.joy.control.model.Constants.SINGLE_SOLENOID_SET
import com.bdtd.joy.control.model.Constants.SINGLE_SOLENOID_SPRAG_EXT
import com.bdtd.joy.control.model.Constants.SINGLE_SOLENOID_SPRAG_RET
import com.bdtd.joy.control.model.Constants.SINGLE_SOLENOID_WATER_CURTAIN
import com.bdtd.joy.control.model.Constants.TEMPLATE_SOLENOID_LAS
import com.future.components.client.viewmodel.BaseViewModel

/**
 *
 * @Description:
 * @Author:         future
 * @CreateDate:     2023/4/26 14:19
 */
object JoyHelper {

    /**
     *
     * @Description:    创建跟随顺序信号(启动创建mode=shearer) && 创建自由运行顺序信号(启动创建mode=free_run)
     * @Author:         future
     * @CreateDate:     2023/4/13 15:31
     */
    fun shearerPrime(viewModel: BaseViewModel, pos: Int, dir: String, func: String, mode: String, actionSuccess: () -> Unit, actionError: () -> Unit = {}) {
        JoyManager.shearerPrime(viewModel,pos, dir, func, mode, actionSuccess, actionError)
    }

    /**
     *
     * @Description:    激活顺序信号(启动自由运行模式)
     * @Author:         future
     * @CreateDate:     2023/4/13 15:32
     */
    fun initiate(viewModel:BaseViewModel,func: String, pos: Int, actionSuccess:() -> Unit, actionError:() -> Unit = {}) {
        JoyManager.initiate(viewModel, func, pos, actionSuccess, actionError)
    }

    /**
     *
     * @Description:    升级顺序信号
     * @Author:         future
     * @CreateDate:     2023/4/13 16:13
     */
    fun promote(viewModel:BaseViewModel,func: String, actionSuccess:() -> Unit, actionError:() -> Unit = {}) {
        JoyManager.promote(viewModel, func, actionSuccess, actionError)
    }

    /**
     *
     * @Description:    暂停顺序信号 && 取消暂停顺序信号
     * @Author:         future
     * @CreateDate:     2023/4/13 16:17
     */
    fun pause(viewModel:BaseViewModel,pos: Int, func: String, mode: String, pause: Boolean?, actionSuccess:() -> Unit, actionError:() -> Unit = {}) {
        JoyManager.pause(viewModel, pos, func, mode, pause, actionSuccess, actionError)
    }

    /**
     *
     * @Description:    删除启动
     * @Author:         future
     * @CreateDate:     2023/4/13 16:24
     */
    fun delete(viewModel:BaseViewModel,pos: Int, func: String, mode: String, actionSuccess:() -> Unit, actionError:() -> Unit = {}) {
        JoyManager.delete(viewModel, pos, func, mode, actionSuccess, actionError)
    }

    /**
     *
     * @Description:    楔形点
     * @Author:         future
     * @CreateDate:     2023/4/13 16:53
     */
    fun wedge(viewModel:BaseViewModel,shieldNum: Int, targetTravel: Int, actionSuccess:() -> Unit, actionError:() -> Unit = {}) {
        JoyManager.wedge(viewModel, shieldNum, targetTravel, actionSuccess, actionError)
    }

    /**
     *
     * @Description:    删除所有楔形点
     * @Author:         future
     * @CreateDate:     2023/4/13 16:53
     */
    fun deleteAllWedge(viewModel:BaseViewModel, actionSuccess:() -> Unit, actionError:() -> Unit = {}) {
        JoyManager.deleteAllWedge(viewModel, actionSuccess, actionError)
    }

    /**
     *
     * @Description:    标记支架  && 取消标记支架
     * @Author:         future
     * @CreateDate:     2023/4/13 16:58
     */
    fun markOrCancelShield(viewModel:BaseViewModel,shieldNum: Int, mark: Boolean, actionSuccess:() -> Unit, actionError:() -> Unit = {}) {
        JoyManager.markOrCancelShield(viewModel, shieldNum, mark, actionSuccess, actionError)
    }

    /**
     *
     * @Description:
     * @Author:         future
     * @CreateDate:     2023/4/13 17:09
     */
    fun clientOTP(viewModel:BaseViewModel,pwd: String, actionSuccess:() -> Unit, actionError:(Int) -> Unit = {}) {
        JoyManager.clientOTP(viewModel, pwd, actionSuccess, actionError)
    }

    /**
     *
     * @Description:    释放压力
     * @Author:         future
     * @CreateDate:     2023/4/13 17:09
     */
    fun releasePush(viewModel:BaseViewModel, actionSuccess:() -> Unit, actionError:() -> Unit = {}) {
        JoyManager.releasePush(viewModel, actionSuccess, actionError)
    }

    /**
     *
     * @Description:    取消端头自动化
     * @Author:         future
     * @CreateDate:     2023/4/21 10:44
     */
    fun automationCancel(viewModel:BaseViewModel, actionSuccess:() -> Unit, actionError:() -> Unit = {}) {
        JoyManager.automationCancel(viewModel, actionSuccess, actionError)
    }


    /**
      *
      * @Description:    单控护帮板控制
      * @Author:         future
      * @param           switch true 升 false 收
      * @CreateDate:     2023/4/26 14:45
     */
    fun spragControl(viewModel:BaseViewModel,support: Int,switch:Boolean, actionSuccess:() -> Unit = {}, actionError:(Int) -> Unit = {}){
        JoyManager.prsControl(viewModel,support,if (switch) SINGLE_SOLENOID_SPRAG_EXT else SINGLE_SOLENOID_SPRAG_RET,-1,ConfigHelper.getSpragTimeout(),actionSuccess, actionError)
    }

    /**
     *
     * @Description:    成组护帮板控制
     * @Author:         future
     * @param           switch true 升 false 收
     * @CreateDate:     2023/4/26 14:45
     */
    fun spragBankControl(viewModel:BaseViewModel,support: Int,switch:Boolean, actionSuccess:() -> Unit = {}, actionError:(Int) -> Unit = {}){
        JoyManager.prsBankControl(viewModel,support,if (switch) GROUP_SOLENOID_SPRAG_EXT else GROUP_SOLENOID_SPRAG_RET,-1,ConfigHelper.getSpragTimeout(),actionSuccess, actionError)
    }

    /**
     *
     * @Description:    单控立柱控制
     * @Author:         future
     * @param           switch true 升 false 收
     * @CreateDate:     2023/4/26 14:45
     */
    fun columnControl(viewModel:BaseViewModel,support: Int,switch:Boolean, actionSuccess:() -> Unit = {}, actionError:(Int) -> Unit = {}) {
        JoyManager.prsControl(viewModel,support,if(switch) SINGLE_SOLENOID_SET else SINGLE_SOLENOID_LOWER,-1,ConfigHelper.getColumnTimeout(),actionSuccess, actionError)
    }

    /**
     *
     * @Description:    成组立柱控制
     * @Author:         future
     * @param           switch true 升 false 收
     * @CreateDate:     2023/4/26 14:45
     */
    fun columnBankControl(viewModel:BaseViewModel,support: Int,switch:Boolean, actionSuccess:() -> Unit = {}, actionError:(Int) -> Unit = {}) {
        when(switch){
            true->JoyManager.prsBankControl(viewModel,support,GROUP_SOLENOID_SET,-1,ConfigHelper.getColumnTimeout(),actionSuccess, actionError)
            else ->viewModel.postMessage("成组无法立柱降")
        }
    }

    /**
     *
     * @Description:    单控底座控制
     * @Author:         future
     * @param           switch true 抬底 false 拉架
     * @CreateDate:     2023/4/26 14:45
     */
    fun baseControl(viewModel:BaseViewModel,support: Int,switch:Boolean, actionSuccess:() -> Unit = {}, actionError:(Int) -> Unit = {}){
        JoyManager.prsControl(viewModel,support,if(switch) SINGLE_SOLENOID_BASE_LIFT else SINGLE_SOLENOID_ADVANCE,-1,ConfigHelper.getBaseTimeout(),actionSuccess, actionError)
    }

    /**
     *
     * @Description:    成组底座控制
     * @Author:         future
     * @param           switch true 抬底 false 拉架
     * @CreateDate:     2023/4/26 14:45
     */
    fun baseBankControl(viewModel:BaseViewModel,support: Int,switch:Boolean, actionSuccess:() -> Unit = {}, actionError:(Int) -> Unit = {}){
        when(switch){
            false -> JoyManager.prsBankControl(viewModel,support,GROUP_SOLENOID_ADVANCE,-1,ConfigHelper.getBaseTimeout(),actionSuccess, actionError)
            else-> viewModel.postMessage("成组无法抬底")
        }
    }

    /**
     *
     * @Description:    单控侧护板控制
     * @Author:         future
     * @param           switch true 伸 false 收
     * @CreateDate:     2023/5/19 16:57
     */
    fun sideShieldControl(viewModel:BaseViewModel,support: Int,switch:Boolean, actionSuccess:() -> Unit = {}, actionError:(Int) -> Unit = {}){
        JoyManager.prsControl(viewModel,support,if(switch) SINGLE_SIDE_SHIELD_EXT else SINGLE_SIDE_SHIELD_RET,-1,ConfigHelper.getSideShieldTimeout(),actionSuccess, actionError)
    }

    /**
     *
     * @Description:    成组侧护板控制
     * @Author:         future
     * @param           switch true 伸 false 收
     * @CreateDate:     2023/5/19 16:57
     */
    fun sideShieldBankControl(viewModel:BaseViewModel,support: Int,switch:Boolean, actionSuccess:() -> Unit = {}, actionError:(Int) -> Unit = {}){
        JoyManager.prsBankControl(viewModel,support,if(switch) GROUP_SIDE_SHIELD_EXT else GROUP_SIDE_SHIELD_RET,-1,ConfigHelper.getSideShieldTimeout(),actionSuccess, actionError)
    }

    /**
     *
     * @Description:    单控推溜控制
     * @Author:         future
     * @CreateDate:     2023/5/19 16:57
     */
    fun pushControl(viewModel:BaseViewModel,support: Int, actionSuccess:() -> Unit = {}, actionError:(Int) -> Unit = {}){
        JoyManager.prsControl(viewModel,support,SINGLE_SOLENOID_PUSH,-1,ConfigHelper.getPushTimeout(),actionSuccess, actionError)
    }

    /**
     *
     * @Description:    成组推溜控制
     * @Author:         future
     * @CreateDate:     2023/5/19 16:57
     */
    fun pushBankControl(viewModel:BaseViewModel,support: Int, actionSuccess:() -> Unit = {}, actionError:(Int) -> Unit = {}){
        JoyManager.prsBankControl(viewModel,support,GROUP_SOLENOID_PUSH,-1,ConfigHelper.getPushTimeout(),actionSuccess, actionError)
    }

    /**
     *
     * @Description:    单控水喷雾控制
     * @Author:         future
     * @CreateDate:     2023/5/19 16:57
     */
    fun waterCurtainControl(viewModel:BaseViewModel,support: Int, actionSuccess:() -> Unit = {}, actionError:(Int) -> Unit = {}){
        JoyManager.prsControl(viewModel,support,SINGLE_SOLENOID_WATER_CURTAIN,-1,ConfigHelper.getDefaultTimeout(),actionSuccess, actionError)
    }

    /**
     *
     * @Description:    成组水喷雾控制
     * @Author:         future
     * @CreateDate:     2023/5/19 16:57
     */
    fun waterCurtainBankControl(viewModel:BaseViewModel,support: Int, actionSuccess:() -> Unit = {}, actionError:(Int) -> Unit = {}){
        JoyManager.prsBankControl(viewModel,support,GROUP_SOLENOID_WATER_CURTAIN,-1,ConfigHelper.getDefaultTimeout(),actionSuccess, actionError)
    }

    /**
     *
     * @Description:    单控LAS控制
     * @Author:         future
     * @CreateDate:     2023/5/19 16:57
     */
    fun lasControl(viewModel:BaseViewModel,support: Int, actionSuccess:() -> Unit = {}, actionError:(Int) -> Unit = {}){
        JoyManager.prsControlTemplate(viewModel,support,TEMPLATE_SOLENOID_LAS,-1,actionSuccess, actionError)
    }

    /**
     * @Description:    PRS控制话路
     * @Author:         future
     * @param           startSupport 开始支架
     * @param           endSupport 结束支架
     * @param           switch true 开始 false 结束
     * @CreateDate:     2023/5/11 10:44
     */
    fun prsSession(viewModel: BaseViewModel, startSupport: Int, endSupport: Int, switch: Boolean, actionSuccess:() -> Unit, actionError:() -> Unit = {}) {
        JoyManager.prsSession(viewModel, startSupport, endSupport, switch, actionSuccess, actionError)
    }

    /**
     * @Description:    覆盖
     * @Author:         future
     * @param           func 单控|模板|成组
     * @param           error 错误码
     * @CreateDate:     2023/5/11 10:44
     */
    fun override(viewModel: BaseViewModel, func: String, error: Int, actionSuccess:(override: Int) -> Unit, actionError:() -> Unit = {}) {
        JoyManager.override(viewModel, func, error, actionSuccess, actionError)
    }

    /**
     *
     * @Description:    互帮板自动参数
     * @Author:         future
     * @CreateDate:     2023/4/21 10:44
     */
    fun autoParam(viewModel:BaseViewModel, auto: String, actionSuccess:() -> Unit, actionError:() -> Unit = {}) {
        JoyManager.autoParam(viewModel, auto, actionSuccess, actionError)
    }

    /**
     *
     * @Description:    空顶区域设置
     * @Author:         future
     * @CreateDate:     2023/4/21 10:44
     */
    fun cavityRegion(viewModel:BaseViewModel, startShield: Int, endShield: Int, coalCuttingNum: Int, actionSuccess:() -> Unit, actionError:() -> Unit = {}) {
        JoyManager.cavityRegion(viewModel, startShield, endShield, coalCuttingNum, actionSuccess, actionError)
    }

    /**
     *
     * @Description:    清除拉架故障
     * @Author:         future
     * @CreateDate:     2023/4/21 10:44
     */
    fun clearAdvFault(viewModel:BaseViewModel, support: Int, actionSuccess:() -> Unit, actionError:() -> Unit = {}) {
        JoyManager.clearAdvFault(viewModel, support, actionSuccess, actionError)
    }

    /**
     *
     * @Description:    清除拉架故障
     * @Author:         future
     * @CreateDate:     2023/4/21 10:44
     */
    fun canCellAndMark(viewModel:BaseViewModel, actionSuccess:() -> Unit, actionError:() -> Unit = {}) {
        JoyManager.canCellAndMark(viewModel, actionSuccess, actionError)
    }

    /**
     *
     * @Description:    更新CCU地址
     * @Author:         future
     * @CreateDate:     2023/5/29 15:17
     */
    fun updateApiService(){
        JoyManager.updateApiService()
    }
}