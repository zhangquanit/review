package net.medlinker.medlinker.reactnative;

import java.util.HashMap;
import java.util.Map;

/**
 * rn 模块和pagename配置
 *
 * @author jiantao
 * @date 2018/4/18
 */
public class ModuleConfig {
    /**
     * 统一默认Module
     */
    public static final String RN_COMPONENT_DEFAULT = "FakeApp";
    /**
     * 执业客服
     */
    public static final String RN_COMPONENT_CS = "CustomerService";
    /**
     * 互联网备案模块
     */
    public static final String RN_COMPONENT_IR = "InternetRecord";
    /**
     * 时空模块
     */
    public static final String RN_COMPONENT_TS = "TimeSpace";

    /**
     * 天使答人
     */
    public static final String RN_COMPONENT_ANGEL_QA = "AngelQA";

    /**
     * 任务系统
     */
    public static final String RN_COMPONENT_MISSIONCENTER = "MissionCenter";

    /**
     * 客服页面
     */
    public static final String RN_PAGE_CS_HOME = "CSHome";
    /**
     * 我的名片
     */
    public static final String RN_PAGE_CS_MYNAMECARD = "CSMyNameCard";
    /**
     * 医师认证页面
     */
    public static final String RN_PAGE_IR_PHYSICIAN_CERTIFICATION = "IRPhysicianCertification";
    /**
     * 多点执业
     */
    public static final String RN_PAGE_IR_MULTIPOINT_WORKRECORD = "IRMultiPointWorkRecord";
    /**
     * 时空首页
     */
    public static final String RN_PAGE_TS_HOME = "TSHome";
    /**
     * 时空发布/分享发布
     */
    public static final String RN_PAGE_TS_PUBLISH = "TSPublish";
    /**
     * 天使答人首页
     */
    public static final String RN_PAGE_ANGEL_QA = "AQAHome";

    /**
     * page 和 modulename映射关系，兼容旧版协议
     */
    static Map<String, String> sPageMap;

    static {
        sPageMap = new HashMap<>(64);
        // 执业客服
        sPageMap.put("CSMyNameCard", RN_COMPONENT_CS);
        sPageMap.put("CSHome", RN_COMPONENT_CS);

        // 天使答人
        sPageMap.put("AQAHome", RN_COMPONENT_ANGEL_QA);
        sPageMap.put("AQAPlayGame", RN_COMPONENT_ANGEL_QA);
        sPageMap.put("AQRankingList", RN_COMPONENT_ANGEL_QA);
        sPageMap.put("AQMissionCenter", RN_COMPONENT_ANGEL_QA);
        sPageMap.put("AQPersonCenter", RN_COMPONENT_ANGEL_QA);
        sPageMap.put("AQActiveRulePage", RN_COMPONENT_ANGEL_QA);
        sPageMap.put("AQAchievementDetailListPage", RN_COMPONENT_ANGEL_QA);
        sPageMap.put("AQAchievementListPage", RN_COMPONENT_ANGEL_QA);
        sPageMap.put("AQLevelDetail", RN_COMPONENT_ANGEL_QA);
        sPageMap.put("AQResultShare", RN_COMPONENT_ANGEL_QA);

        //互联网执业
        sPageMap.put("IRSubmitSuccess", RN_COMPONENT_IR);
        sPageMap.put("IRPhysicianCertification", RN_COMPONENT_IR);
        sPageMap.put("IRAgreeMulti", RN_COMPONENT_IR);
        sPageMap.put("IRQualificationProof", RN_COMPONENT_IR);
        sPageMap.put("IRMultiPointWorkRecord", RN_COMPONENT_IR);
        sPageMap.put("IRSearchHospital", RN_COMPONENT_IR);
    }

    /**
     * 解析RouterName对应的ModuleName
     *
     * @param routeName
     * @return
     */
    public static String parseModule(String routeName) {
        return sPageMap.get(routeName);
    }
}
