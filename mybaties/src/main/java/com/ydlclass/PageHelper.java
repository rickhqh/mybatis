package com.ydlclass;

/**
 * @author 小松
 */
public class PageHelper {
    public static Page PAGE=null;

//    第几条开始，偏移量
    public static void startPage(int from,int offset){
        PAGE=new Page(from,offset);



    }
    public static  class Page
    {
        private int from;
        private int offset;

        public int getFrom() {
            return from;
        }

        public void setFrom(int from) {
            this.from = from;
        }

        public int getOffset() {
            return offset;
        }

        public void setOffset(int offset) {
            this.offset = offset;
        }

        public Page(int from, int offset) {
            this.from = from;
            this.offset = offset;
        }
    }


}
