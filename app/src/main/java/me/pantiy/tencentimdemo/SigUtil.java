package me.pantiy.tencentimdemo;

import java.util.HashMap;

/**
 * Created by Pantiy on 2018/4/20.
 * Copyright © 2016 All rights Reserved by Pantiy
 */
public final class SigUtil {

    private HashMap<String, String> mUserMap;

    public static SigUtil touch() {
        return SigUtilHolder.INSTANCE;
    }

    private SigUtil() {
        mUserMap = new HashMap<>();
        mUserMap.put("user_one", "eJxlj0tPg0AUhff8CsLa6DwYBJMu0GJthBhbYptuJgMzpTeVR5gppWn871psIoln*333npyzZdu2k8bLW5Hn9aEy3Jwa5dgPtoOcmz-YNCC5MJy28h9UfQOt4mJrVDtAzBgjCI0dkKoysIWrcdCq5XWlRoaWez7U-L5wf*595hE2VqAYYBK9P80jcWRpEjwn1NduvEB1FyQpvUPxBnmn9SaLVh957i5xH2chPIqyaIqMClmEK5hOcdiHyXr2Wc5e8NvuHpDX71*7*U77*jiZjCoNlOq6ibg4cAOfjGinWg11NQgEYYYJRZc41pf1DRPuXXY_");
        mUserMap.put("user_two", "eJxlj1FPgzAUhd-5FaTPxpVCzTDxxWGykSHicNGnhkHp7sgolnZsM-53FZfYxPP6ffeenA-HdV2UL1fXRVlK02qmTx1H7q2LMLr6g10HFSs081X1D-JjB4qzotZcjdCjlBKMbQcq3mqo4WKYniumB2kZfdWwseb3RfB9P6U3hNoKiBEmDy*zRRY9GvyeDX75-LbU4hDt70ksik1mhkkexP122K0lqHwXeUO2EDGI*Xn2moJfr8tK1yd5nNcrXPRPJm3SLZ3gxG-OG8hkcmdVatjzyyYSeGEQhlOLHrjqQbajQLBHPeLjnyDn0-kChu1gTA__");
    }

    public String getUserSig(String userId) {
        return mUserMap.get(userId);
    }

    private static class SigUtilHolder {
        private static SigUtil INSTANCE = new SigUtil();
    }
}