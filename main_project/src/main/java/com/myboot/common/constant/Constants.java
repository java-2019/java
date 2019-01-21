package com.myboot.common.constant;

/**
 * 常量信息
 *
 * Created by majf
 */
public class Constants {

    /**
     * 分批保存，暂定一次保存1000条
     */
    public static final int DEFAULT_SAVE_DATALIMIT= 1000;

    /**
     * 统计默认开始时间
     */
    public static final String DEFAULT_START_DATE= "2018-05-01";

    /**
     * 统计类型：快捷买单
     */
    public static final String STAT_TYPE_FAST_PAY = "fast_pay";

    /**
     * 统计类型：群发消息
     */
    public static final String STAT_TYPE_GROUP_MESSAGE = "group_message";

    /**
     * 统计类型：二维码扫描
     */
    public static final String STAT_TYPE_QRCODE = "qrcode";

    /**
     * 统计类型：优惠券
     */
    public static final String STAT_TYPE_COUPON = "coupon";

    /**
     * 统计类型：大转盘
     */
    public static final String STAT_TYPE_LUCKY_WHEEL = "lucky_wheel";

    /**
     * 统计类型：电子期刊
     */
    public static final String STAT_TYPE_JOURNALE = "journal";

    /**
     * 统计类型：限时抢购
     */
    public static final String STAT_TYPE_PAID_SERVICE = "paid_service";

    /**
     * 统计类型：次卡商城
     */
    public static final String STAT_TYPE_ITEM_CARD = "item_card";

    /**
     * 统计类型：支付
     */
    public static final String STAT_TYPE_PAYMENT = "payment";

    /**
     * 统计类型：核销
     */
    public static final String STAT_TYPE_VERIFY = "verify";

    /**
     * 统计类型：会所的用户数据
     */
    public static final String STAT_TYPE_CLUN_USER = "club_user";

    /**
     * 统计类型：结算
     */
    public static final String STAT_TYPE_SETTLE = "settle";

    /**
     * 统计类型：注册用户
     */
    public static final String STAT_TYPE_REGISTER = "register";

    /**
     * 统计类型：技师数据
     */
    public static final String STAT_TYPE_TECH_VISIT = "tech_visit";

    /**
     * 统计类型：会员
     */
    public static final String STAT_TYPE_MEMBER = "member";

    /**
     * 统计类型：在线预约
     */
    public static final String STAT_TYPE_ORDER = "order";

    /**
     * 统计类型：积分
     */
    public static final String STAT_TYPE_CREDIT_INCOME = "credit_income";

    /**
     * 统计类型：在线客服
     */
    public static final String STAT_TYPE_CUSTOMER_SERVICE = "customer_service";

    /**
     * 统计类型：用户打赏
     */
    public static final String STAT_TYPE_USER_REWARD = "user_reward";

    /**
     * 统计类型：用户点评
     */
    public static final String STAT_TYPE_USER_COMMENT = "user_comment";

    /**
     * 统计类型：首页
     */
    public static final String STAT_TYPE_HOME = "home";

    /**
     * 统计类型：累计数据
     */
    public static final String STAT_TYPE_CUMULATIVE = "cumulative";

    /**
     * 管理者区域权限：管理者查看全部区域
     */
    public static final String ROLE_PERMISSIONS_ADMIN = "0";

    /**
     * 管理者区域权限：查看其他区域
     */
    public static final String ROLE_PERMISSIONS_OTHER = "6";

    /**
     * 会所所在区域：佛山区域
     */
    public static final int CLUB_REGION_FOSHAN = 1;

    /**
     * 会所所在区域：粤东区域，粤东包括汕头，汕尾，潮州，揭阳，普宁
     */
    public static final int CLUB_REGION_YUEDONG = 2;

    /**
     * 会所所在区域：东莞区域
     */
    public static final int CLUB_REGION_DONGGUANG = 3;

    /**
     * 会所所在区域：深圳区域
     */
    public static final int CLUB_REGION_SHENZHEN = 4;

    /**
     * 会所所在区域：广州区域
     */
    public static final int CLUB_REGION_GUANGZHOU = 5;

    /**
     * 会所所在区域：其他区域
     */
    public static final int CLUB_REGION_OTHER = 6;

    public static final int ROLE_PERMISSION_ADMIN = 0;

    /**
     *城市区域集合
     */
    public static final String CITY_REGION_LIST = "深圳,广州,东莞,佛山,汕头,汕尾,揭阳,普宁";

    /**
     * 帐号状态：正常使用
     */
    public static final int STATUS_NORMAL = 1 ;

    /**
     * 同步spa会所数据
     */
    public static final String SPA_CLUB_SYNCHRONIZATION = "spa_club_sync";

    /**
     * 登录状态:重新登录被踢
     */
    public static final String SESSION_STATUS_CONCURRENT = "concurrent";
    /**
     * 登录状态：正常可用状态
     */
    public static final String SESSION_STATUS_VALID = "valid";
    /**
     * 登录状态:登出
     */
    public static final String SESSION_STATUS_LOGOUT = "logout";

    /**
     * 营销短信错误编码: HTML内容
     */
    public static final String MESSAGE_CODE_HTML_MESSAGE = "SHOW_HTML_MESSAGE";
}
