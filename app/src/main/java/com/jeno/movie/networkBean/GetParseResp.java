package com.jeno.movie.networkBean;

/**
 {
 "code": 200,
 "msg": "success",
 "data": "VtEmXN0+W8fXyqK58Tkhz2EluC2kRVvXObyjERKVXFptfw3gHRJu592pYHjv7Nmwuaxd5cLEAx8Mkoi5bXTUo0TvzqhlUcjcMc4nEJg9O041q6HI0w1Ak0NZNbeuw4GBfwvzx5Vq6aiKbLmUWJdUWmF5eJkQEMhotvA6EIhUWH0=$gKM1aRC1is8OQQm/ra13jAC6iKlJP+sShSN0yFSxbLbVmf81gmJDiqSB2qD6HxKd0iVS0wy8m3wn4psoZzKtPq56wGhzIM6NisE/DS5EjSounpH+pZpTajMobPNOraMcPQjxtxu3uciWYJEf2yCE4iYRukn7e0l+8y66PvXvyss=$YKrqq38BxcGyiAz0NbH//MSug1F42+Tr5D6sYYCBzPyi9XQA9x69Ev5QQlwYKS+ERScp7lQeFzlfBNgbd/MRxUtMXXeZcAWR9C9ElRxazLUvFAfTWsNCgIkzSFE4aOsuDziQu5CdaltFpGyGmHuSx4eH2fvRHLnQvjp1BTFgfv4=$V6ieojfNPeFoQ/pj0qaavHqOash6kXCwVzdeSHhJZ8rCwekAf3ov74im71infvVoREIrljjUsmemy6vCYzA5PdOz/vnPYGBIBOhdJsbpCHtiMqKrhGp2oU5f9Yq16PsyvJEtoJcgOipg6d263zOOxWn78zu5j11oCs+mhJFQkUY=$eEG9c9wlqclF5+ST71k33R5gomhZeEh/ZsokFNyGYBYh2OZvBJCal1Ftr190DGFczUVR09pvH0ImVmQeIhtPEsNpdBT3JQPm0hvVYy+sdvRdQuxqVwMGm7mOY3wxVEM8FnT3NW6Kl77VnNXZCg6MGSBURYgrEiBHKfnTBbQY2iA=$WO+7UZRVARdqFQN9+xzhNgFvuibdV749UqBw7JNxSdWGzd5yRT+fxedQihBf78YSXufENuExiU1AtEyL4hcwhN449pZc39lH2sO/wncSwxVlQPiGDUJ9bEWLEFU9OSeqU+Z8LTacYQo/b1zTnDyufEFdBz8Y69ACXw9KVLoe16U=$Jnb721h+GmwvMUZl3RjvRIPntylfvCXZHHY8Ie61ifBuG9H/uY7ODT1AHf31u+PUqHwB5FwwNnNLtXbRt2XSq+mXKhRFoSkivBsluaO1GDiT02PX/gqMuEwai1QsbbUiZ+DSD0roAO7ZGkiupvhpOGbLE+3hB9qlHeKgPrQ3yeA=$Yf5X2VpQ3eLEX6AzRuGGEUIJTfULsXZ1MLUUxhYED6rgm8Tkt8cu264TNqmfV08hx3R0G+nPfZEiSWyxre5TScCKLG5ZSAEIgjKdAy1sINO9owCDwFpx/VGFlDnmX99BLP2PqZv4oXJmSXmP3d+laJIO742wfx/L6i1cwDWr0k8="
 }
 */
public class GetParseResp {
    private String TAG = "OKHTTP";
    private int code;
    private String msg;
    private Data data;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public String getDataUrl() {
        if (data != null) {
            return data.getUrl();
        }else {
            return null;
        }
    }

    public int getDataCur() {
        if (data != null) {
            return data.getCur();
        }else {
            return 0;
        }
    }

    public int getDataTotal() {
        if (data != null) {
            return data.getTotal();
        }else {
            return 0;
        }
    }

    class Data {
        private String url;
        private int cur;
        private int total;

        public int getCur() {
            return cur;
        }

        public int getTotal() {
            return total;
        }

        public String getUrl() {
            return url;
        }
    }

}
