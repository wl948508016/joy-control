package com.bdtd.joy.control.model

/**
 *
 * @Description:
 * @Author:         future
 * @CreateDate:     2023/5/18 18:02
 */
object Constants {

    const val URL_TAG="URL_TAG"

    /**
     * 新文档的覆盖标记
     */
    const val OVERRIDE_DIR_SOL = "dirsol" // 单控
    const val OVERRIDE_DIR_TEMP = "dirtemp" // 模板控制
    const val OVERRIDE_BANK_SOL = "banksol" // 成组控制
    const val OVERRIDE_ERROR_CODE_LOWER = 9 // Too many adjacent lowers
    const val OVERRIDE_ERROR_CODE_LOCATION = 10 // shearer at location

    /**
     * 新文档的单控电磁阀
     */
    const val SINGLE_SOLENOID_SPRAG_EXT = "sprag_ext" // 互帮板升
    const val SINGLE_SOLENOID_SPRAG_RET = "sprag_ret" // 互帮板降
    const val SINGLE_SOLENOID_SET = "set" // 立柱升
    const val SINGLE_SOLENOID_LOWER = "lower" // 立柱降
    const val SINGLE_SOLENOID_PUSH = "push" // 推溜
    const val SINGLE_SOLENOID_BASE_LIFT = "base_lift" // 底座抬底
    const val SINGLE_SOLENOID_ADVANCE = "advance" // 底座拉架
    const val SINGLE_SOLENOID_CREEP_ADVANCE = "creep_advance" // 阻尼阀
    const val SINGLE_SIDE_SHIELD_EXT = "side_shield_ext" // 侧护板伸
    const val SINGLE_SIDE_SHIELD_RET = "side_shield_ret" // 侧护板收
    const val SINGLE_SOLENOID_WATER_CURTAIN = "water_curtain" // 水喷雾

    /**
     * 新文档的成组控制电磁阀
     */
    const val GROUP_SOLENOID_SPRAG_EXT = "sprag_ext" // 互帮板升
    const val GROUP_SOLENOID_SPRAG_RET = "sprag_ret" // 互帮板降
    const val GROUP_SOLENOID_SET = "set" // 立柱升
    const val GROUP_SOLENOID_PUSH = "push" // 推溜
    const val GROUP_SOLENOID_ADVANCE = "advance" // 底座拉架
    const val GROUP_SIDE_SHIELD_EXT = "side_shield_ext" // 侧护板伸
    const val GROUP_SIDE_SHIELD_RET = "side_shield_ret" // 侧护板收
    const val GROUP_SOLENOID_WATER_CURTAIN = "water_curtain" // 水喷雾

    /**
     * 模板控制电磁阀
     */
    const val TEMPLATE_SOLENOID_LAS = "las" // 降拉升
}