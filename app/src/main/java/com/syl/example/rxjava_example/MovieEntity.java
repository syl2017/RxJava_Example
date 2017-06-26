package com.syl.example.rxjava_example;

import java.util.List;

/**
 * Created by syl on 2017/6/25.
 */

public class MovieEntity {



    private int count;
    private int start;
    private int total;
    private String title;
    private List<SubjectsEntity> subjects;

    @Override
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("count=" + count +" start"+start+ " \ntotal=" + total + " title=" + title);
        if (subjects != null) {
            stringBuffer.append("\nsubjects" + subjects.toString());
        }
        return stringBuffer.toString();
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSubjects(List<SubjectsEntity> subjects) {
        this.subjects = subjects;
    }

    public int getCount() {
        return count;
    }

    public int getStart() {
        return start;
    }

    public int getTotal() {
        return total;
    }

    public String getTitle() {
        return title;
    }

    public List<SubjectsEntity> getSubjects() {
        return subjects;
    }

    public static class SubjectsEntity {

        @Override
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("  title "+ title + "collect_count=" + collect_count + " orginal_title=" + original_title + " subtype=" + subtype + " \nyear=" + year + " \nalt=" + alt + " \nid" + id);
            return stringBuilder.toString();
        }


        private String title;
        private int collect_count;
        private String original_title;
        private String subtype;
        private String year;

        private String alt;
        private String id;




        public void setTitle(String title) {
            this.title = title;
        }

        public void setCollect_count(int collect_count) {
            this.collect_count = collect_count;
        }

        public void setOriginal_title(String original_title) {
            this.original_title = original_title;
        }

        public void setSubtype(String subtype) {
            this.subtype = subtype;
        }

        public void setYear(String year) {
            this.year = year;
        }



        public void setAlt(String alt) {
            this.alt = alt;
        }

        public void setId(String id) {
            this.id = id;
        }





        public String getTitle() {
            return title;
        }

        public int getCollect_count() {
            return collect_count;
        }

        public String getOriginal_title() {
            return original_title;
        }

        public String getSubtype() {
            return subtype;
        }

        public String getYear() {
            return year;
        }


        public String getAlt() {
            return alt;
        }

        public String getId() {
            return id;
        }




    }
}
