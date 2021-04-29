package com.dingding.purchase.config;

public class AlipayConfig {

        // 商户appid
        public static String APPID = "2021000117637886";

        // 私钥 pkcs8格式的
        public static String RSA_PRIVATE_KEY = "MIIEuwIBADANBgkqhkiG9w0BAQEFAASCBKUwggShAgEAAoIBAQCX3OnlTMFcWyRXa2A4TlYUpuTOgj4Ne5jghOtmpm7LUwBPu7JF1nTL3XktmQf8F6uwkQEFSqKXJKofGpdaXpi/dGR2OAIOxBQekRTrRvEVyxz9cdnpBUdZtbI+SqJj5SzZmL3cfCoAyagOTUTCyPDukZ3HBGpEa0Fe8d2VhLpYEJ77e2WkhlvkvczR2uqNmoUM+PnQoDOhdz7QktpP9inRuTeExwalgtXs8vFpEz9y0J76m59Enc9DJ8iOfDfV+Ycd7IfYFlh1QcJJlIYCtMU0uoWubopRIlx0H2daT/4R/Ttzj73Je4AfHLeajJh0yJQFEbmJzYkkKppaliMUr/IBAgMBAAECggEARatRmqKV6rrdd0WJhQtbLmpKefsCYMkdH5ObMyKTna/carffV27FcmmOmD6mjR6G46ACad0Nrm1hppdabFkNlnfdRub2+rM1z7gbcry/CZJ/ID7FC8QiMaPLvAwdZ9rRspoCME6+LlJVSQplyz7IWYpwacc6wVy7RsWLNHcJzjBM80P/00HDrbzo+oOoZgyb7eTvmifXmsEm9z/S8YFGaoLwduWWXfcFr2S9OxpUCBgBXbUIePnGBWBfXK40ut9uXo9itMB3CR7X3ZfqcKlc41X/lVDOqkJsRbthVj/UoV36hQ+AcS0E8d3Si+M7JCYW5fS0kjUzaX4Povfaz3FPgQKBgQDHH0F+1z3y2oXGbVPZEK0ij+CxlEbcaa6jSFSeFmXsLvuzKjWD0b+bEkz5EXnr4dY7OmM7k9QiWWgt1mouGEV9W/UyHERUsKeRgbRy59vI6SYajjdFcQaRa3v7w6rZ/TSQPrXnpOVxvAIrx2MyfOl0NPMg7JT1yElCHwZ2I3LQeQKBgQDDPdi05UfOa0Nk9fOazAT0DMwDPburWqcqIZ5P96iX27VeZadqt7iBJaiJQxUee5JysBt0jKZU1AhqOlj4PiuSkgOVy95ypg2OFH3VN0EFUJQsFu6A4JN8oUYoxN6SrPf6E62H3vVVZC/hWRTNHpGHwkLYyhhKnwxEAp6ye5mbyQJ/VY8YlWfnNnKvCJPxc1O3ESLBaYxM5miCoaZytXrqUJHflRyN4hY+PmOer2bS/IgNhWWRPghHHntMSGXZOfUz5qmcsetzcPB3EA6rerQlvC5yMPbqyOvH5rRIkeWBhClIbLKFdwNjk3s1c5o5BXim10fpMDx3xSRKVh7+p8STkQKBgA1ilxHHykV3ixZH2lyZchl9Kxtpxwz3WRCFX4Ih+3f1wxhmpbSmRcrs1wIaDD+bvy2Hr0otPlYAd1c5CtJyCLCyu/YwFN8NTU6ZTwsoD5garx3asJ07SEE6GUJARPYPT6zgXnw7J9yj0riDT+7EErwY8FZGfKXZA5FCELkxyhUpAoGBAMBbKOkUDk/Y0PzjzInZ4BCzx18kKJhDOO9oX1GPkdAMGlG1kpY0bRhSjIlIvtGIByl34Mvi6Xg/LJ00PFGYT+yt29vSowamfeWBc2Zakg/kOR46pTvv/hlgKUn1jqI4yMixClIzOCQlkfdtXtbInpB5AuzM9kpjS7P5AHiLxnbT";

        // 服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
        public static String notify_url = "http://localhost:8080/shop/alipayTempTransit.html";

        // 页面跳转同步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问 商户可以自定义同步跳转地址
        public static String return_url = "http://localhost:8080/shop/alipayTempTransit.html";

        // 请求网关地址
        public static String URL = "https://openapi.alipaydev.com/gateway.do";

        // 编码
        public static String CHARSET = "UTF-8";

        // 返回格式
        public static String FORMAT = "json";

        // 支付宝公钥
        public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAgA5znXjkk7J+mS+hP0sqt/vv2qdY0dH6vxp/MwGqJkZ/rOSzTtHfcaDkawxq+sOXXnowzHdvPSV1pzQEz7BumIiW8e8aMd2hWG7/c/n/KEjNynKRG992InRxTnFTOlnWnwbkGCeX+M5b+pxO6cYf4F93DuEEsLEpojrnVqqcUVB99u31bXgJk154jxPyvo8QtMJvwo7Enypq8bevBfSvcEy97nKIsZ6g8VQKZJ+7/K1wenzOcUPDMx/lfirkYdeEsGG6/g2nwwWhDYSlpOElZZnC9tGGdHldOigvmwnt2lDo05Q/nTnHs7C/CYNxaTzv0eY7nGU3gV6F1vEiGZuRYQIDAQAB";

        // 日志记录目录定义在 logFile 中
        public static String log_path = "/log";

        // RSA2
        public static String SIGNTYPE = "RSA2";

    }
