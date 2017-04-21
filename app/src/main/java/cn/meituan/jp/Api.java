package cn.meituan.jp;

/**
 * Created by 11608 on 2017/4/13.
 */

/**
 * Route::get('/', 'Auth\AuthController@home');
 Route::get('/test', 'Auth\AuthController@test');

 // 注册
 Route::post('/user/register', 'Auth\AuthController@register');
 // 登录
 Route::post('/user/login', 'Auth\AuthController@login');

 // 个人资料
 Route::get('/users/{user}/profile', 'Auth\UserController@profile');
 // 添加收货地址
 Route::post('/users/{user}/address', 'Auth\UserController@addAddress');

 // 商店列表
 Route::get('/shops', 'Shop\ShopController@shops');
 // 商品类型列表
 Route::get('/food_types', 'Food\FoodController@types');
 // 商品列表
 Route::get('/foods', 'Food\FoodController@foods');

 // 商品的订单列表
 Route::get('/foods/{food}/orders', 'Food\FoodController@orders');
 // 商品的评论列表
 Route::get('/foods/{food}/comments', 'Food\FoodController@comments');

 // 我的订单列表
 Route::get('/users/{user}/orders', 'Auth\UserController@orders');
 // 我的评论列表
 Route::get('/users/{user}/comments', 'Auth\UserController@comments');

 // 发起评论
 Route::post('/orders/{order}/users/{user}/comments', 'User\UserController@publishComment');
 // 下订单
 Route::post('/foods/{food}/users/{user}/placeOrder', 'User\UserController@placeOrder');
 */

public class Api {
    /**
     * 登录
     */
    public static final String LOGIN = "user/login";
    /**
     * 注册
     */
    public static final String REGISTER = "user/register";
    /**
     *个人资料
     */
    public static final String PERSONALINFO = "users/%s/profile";
    /**
     * 添加收货地址
     */

    public static final String ADDTAKEDELIVERYADDRESS = "users/%s/address";

    /**
     * 商店列表
     */
    public static final String GAINSHOPSLIST = "shops";

    /**
     * 商品列表
     */

    public static final String GAINGOODSLIST = "foods";

    /**
     * 商品类型列表
     */

    public static final String GAINGOODSTYPELIST = "food_types";

    /**
     * 商品订单列表
     */

    public static final String GAINGOODSORDERLIST = "foods/%s/orders";

    /**
     * 商品的评价列表
     */

    public static final String GAINGOODSCOMMENTLIST = "foods/%s/comments";

    /**
     * 我的订单列表
     */

    public static final String GAINMYGOODSORDERSLIST = "users/%s/orders";

    /**
     * 我的评论列表
     */

    public static final String GAINMYCOMMENTSLIST = "users/%s/comments";

    /**
     * 发起评论
     */

    public static final String SENDCOMMENTS = "orders/s%/users/%s/comments";
}
