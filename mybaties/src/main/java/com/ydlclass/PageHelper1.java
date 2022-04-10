package com.ydlclass;

/**
 * @author 小松
 */
public class PageHelper1 {
//    public static Page PAGE=null;

    public static ThreadLocal<Page> TL=new ThreadLocal();//线程安全
    static{
        TL.set(new Page());
    }


//    第几条开始，偏移量
    public static void startPage(int from,int offset){
//        PAGE=new Page(from,offset);
    Page page = TL.get();
    page.setOffset(offset);
    page.setFrom(from);


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

        public Page() {
        }
    }


}
