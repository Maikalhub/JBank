package condorcet.Models.Entities;

public class Groups {

    private int group_Id;
    private String FIO_;
    private String group_number;
    private String ryk;

    // Конструктор с параметрами
    public Groups(int group_Id, String FIO_, String group_number, String ryk) {
        this.group_Id = group_Id;
        this.FIO_ = FIO_;
        this.group_number = group_number;
        this.ryk = ryk;
    }

    // Конструктор без параметров (по умолчанию)
    public Groups() {
        // Можно добавить значения по умолчанию, если нужно
    }

    // Геттеры и сеттеры для всех полей
    public int getGroup_Id() {
        return group_Id;
    }

    public void setGroup_Id(int group_Id) {
        this.group_Id = group_Id;
    }

    public String getFIO_() {
        return FIO_;
    }

    public void setFIO_(String FIO_) {
        this.FIO_ = FIO_;
    }

    public String getGroup_number() {
        return group_number;
    }

    public void setGroup_number(String group_number) {
        this.group_number = group_number;
    }

    public String getRyk() {
        return ryk;
    }

    public void setRyk(String ryk) {
        this.ryk = ryk;
    }
}
