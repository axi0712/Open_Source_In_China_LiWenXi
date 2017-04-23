package com.example.administrator.open_source_in_china_liwenxi.model.fragment.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/4/16 0016.
 */

public class Discover_fenleiJavaBean {


    private String softwarecount;
    private List<SoftwareTypeBean> softwareTypes;

    public String getSoftwarecount() {
        return softwarecount;
    }

    public void setSoftwarecount(String softwarecount) {
        this.softwarecount = softwarecount;
    }

    public List<SoftwareTypeBean> getSoftwareTypes() {
        return softwareTypes;
    }

    public void setSoftwareTypes(List<SoftwareTypeBean> softwareTypes) {
        this.softwareTypes = softwareTypes;
    }

    @Override
    public String toString() {
        return "Discover_fenleiJavaBean{" +
                "softwarecount='" + softwarecount + '\'' +
                ", softwareTypes=" + softwareTypes +
                '}';
    }

    public static class SoftwareTypeBean {
        private String name;
        private String tag;

        @Override
        public String toString() {
            return "SoftwareTypeBean{" +
                    "name='" + name + '\'' +
                    ", tag='" + tag + '\'' +
                    '}';
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }
    }
}
