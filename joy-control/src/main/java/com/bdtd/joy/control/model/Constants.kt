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

    /**
     * 间隔时长
     */
    const val DEFAULT_TIMEOUT = "DEFAULT_TIMEOUT" // 默认时长
    const val SPRAG_TIMEOUT = "SPRAG_TIMEOUT" // 护帮板时长
    const val SIDE_SHIELD_TIMEOUT = "SIDE_SHIELD_TIMEOUT" // 侧护板时长
    const val COLUMN_TIMEOUT = "COLUMN_TIMEOUT" // 立柱时长
    const val BASE_TIMEOUT = "BASE_TIMEOUT" // 底座时长
    const val PUSH_TIMEOUT = "PUSH_TIMEOUT" // 推溜时长
}