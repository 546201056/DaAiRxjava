package com.ziyouzhuhe.zhuanqianbao.api;

import java.util.List;

/**
 * Created by Administrator on 2017/12/13.
 */

public class MyMoudel {

    /**
     * menu : {"id":"file","value":"File","popup":{"menuitem":[{"value":"New","onclick":"CreateNewDoc()"},{"value":"Open","onclick":"OpenDoc()"},{"value":"Close","onclick":"CloseDoc()"}]}}
     */

    private MenuBean menu;

    public MenuBean getMenu() {
        return menu;
    }

    public void setMenu(MenuBean menu) {
        this.menu = menu;
    }

    public static class MenuBean {
        /**
         * id : file
         * value : File
         * popup : {"menuitem":[{"value":"New","onclick":"CreateNewDoc()"},{"value":"Open","onclick":"OpenDoc()"},{"value":"Close","onclick":"CloseDoc()"}]}
         */

        private String id;
        private String value;
        private PopupBean popup;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public PopupBean getPopup() {
            return popup;
        }

        public void setPopup(PopupBean popup) {
            this.popup = popup;
        }

        public static class PopupBean {
            private List<MenuitemBean> menuitem;

            public List<MenuitemBean> getMenuitem() {
                return menuitem;
            }

            public void setMenuitem(List<MenuitemBean> menuitem) {
                this.menuitem = menuitem;
            }

            public static class MenuitemBean {
                /**
                 * value : New
                 * onclick : CreateNewDoc()
                 */

                private String value;
                private String onclick;

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }

                public String getOnclick() {
                    return onclick;
                }

                public void setOnclick(String onclick) {
                    this.onclick = onclick;
                }
            }
        }
    }
}
