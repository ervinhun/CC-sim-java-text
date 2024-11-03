public class DrawMap {
    String map;
    public DrawMap() {
        this.map =  "------------------      ----------------------        ------------------\n";
        this.map += "|  Campus pub     |=====|        Outside      |=======| Lecture theater|\n";
        this.map += "------------------      ---------|  |---------        ------------------\n";
        this.map += "                                 |  |\n";
        this.map += "                         ---------------------        ------------------\n";
        this.map += "                         |  Computing lab    |========|  Admin office   |\n";
        this.map += "                         ---------------------        ------|* *|---------\n";
        this.map += "                                                            |* *|\n";
        this.map += "                                                       ------------------\n";
        this.map += "                                                       |    Cellar      |\n";
        this.map += "                                                       ------------------\n";
        System.out.println(map);
    }

}
