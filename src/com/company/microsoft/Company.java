package microsoft;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Company {

    private Map<String, String> staff2Boss = new HashMap<>();
    private Map<String, List<String>> boss2Stuff = new HashMap<>();

    public Map<String, String> getStaff2Boss() {
        return staff2Boss;
    }

    public Map<String, List<String>> getBoss2Stuff() {
        return boss2Stuff;
    }

    static class Detail {
        private String id; // 名字
        private List<Detail> sub; // 下属

        public Detail() {
        }

        public Detail(String id, List<Detail> sub) {
            this.id = id;
            this.sub = sub;
        }

        public String getId() {
            return id;
        }

        public List<Detail> getSub() {
            return sub;
        }

        @Override
        public String toString() {
            return "{" +
                    "id='" + id + '\'' +
                    ", sub=" + sub +
                    '}';
        }
    }

    public static void main(String[] args) {
        String[][] data = {{"1", "A"}, {"2", "B"}, {"A", "B"}};
        Company com = new Company();

        Map<String, String> staff2Boss = com.getStaff2Boss();
        Map<String, List<String>> boss2Stuff = com.getBoss2Stuff();

        for (int i = 0; i < data.length; i++) {
            staff2Boss.put(data[i][0], data[i][1]);
            // 某些领导，可能只是领导，没有上级
            if (staff2Boss.get(data[i][1]) == null) {
                staff2Boss.put(data[i][1], null);
            }

            List<String> stuffs = null;
            if (boss2Stuff.get(data[i][1]) == null) {
                stuffs = new ArrayList<>();
                stuffs.add(data[i][0]);
                boss2Stuff.put(data[i][1], stuffs);
            } else {
                stuffs = boss2Stuff.get(data[i][1]);
                stuffs.add(data[i][0]);
            }
        }
//        System.out.println(boss2Stuff);

        // 找出没有上级的大boss
        String bigBossId = null;
        for (String stuff : staff2Boss.keySet()) {
            if (staff2Boss.get(stuff) == null) {
                bigBossId = stuff;
                break;
            }
        }
//        System.out.println("bigBossId = " + bigBossId);

        Detail dd = com.getStuff(boss2Stuff, bigBossId);
        System.out.println(dd);
    }


    Detail getStuff(Map<String, List<String>> boss2Stuff, String bossId) {
        if (boss2Stuff.get(bossId) != null) {
            List<String> stuff = boss2Stuff.get(bossId);
            List<Detail> stuffDetail = new ArrayList<>();
            for (int i = 0; i < stuff.size(); i++) {
                stuffDetail.add(getStuff(boss2Stuff, stuff.get(i)));
            }
            return new Detail(bossId, stuffDetail);
        } else {
            return new Detail(bossId, null);
        }
    }
}
