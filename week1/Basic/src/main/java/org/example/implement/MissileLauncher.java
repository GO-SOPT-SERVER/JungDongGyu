package org.example.implement;

public class MissileLauncher {
    public static void main(String[] args) {
        HydrogenMissile missile1 = new HydrogenMissile();
        missile1.setName("맑고청량한수소");
        NuclearFissionMissile missile2 = new NuclearFissionMissile();
        missile2.setName("핵분열");
        NuclearFusionMissile missile3 = new NuclearFusionMissile();
        missile3.setName("핵융합");

        destroyWorld(missile1);
        System.out.println();
        destroyWorld(missile2);
        System.out.println();
        destroyWorld(missile3);
    }

    public static void destroyWorld(Missile missile) {
        missile.countDown();
        missile.launch();
        missile.explode();
    }

    static class NameTag {
        String name;

        void setName(String name) {
            this.name = name;
        }
    }

    // 수소 폭탄 미사일
    static class HydrogenMissile extends NameTag implements Missile {

        @Override
        public void launch() {
            System.out.println("수소 폭탄 \"" + this.name + "\" 발사");
        }

        @Override
        public void explode() {
            System.out.println("수소 폭탄 폭발!!");
        }
    }

    // 핵 분열 미사일
    static class NuclearFissionMissile extends NameTag implements Missile, Nuclear {

        @Override
        public void launch() {
            System.out.println("\"" + this.name + "\" 핵분열 미사일 발사");
        }

        @Override
        public void explode() {
            nuclearReaction();
            System.out.println("핵분열 미사일 폭발");
        }

        @Override
        public void nuclearReaction() {
            System.out.println("(핵분열 반응 중...)");
        }
    }

    // 핵 융합 미사일
    static class NuclearFusionMissile extends NameTag implements Missile, Nuclear {
        @Override
        public void launch() {
            System.out.println("\"" + this.name + "\" 미사일 발사");
        }

        @Override
        public void explode() {
            nuclearReaction();
            System.out.println("핵융합 미사일 폭발");
        }

        @Override
        public void nuclearReaction() {
            System.out.println("(핵융합 반응 중...)");
        }
    }

}
