package condorcet.Services;

import condorcet.DataAccessObjects.GroupsDAO;
import condorcet.Models.Entities.Groups;

import java.util.List;

public class GroupsService {

    // Инициализация DAO для работы с группами
    GroupsDAO groupsDAO = new GroupsDAO();

    // Удаляем ошибочную строку, которая вызывает рекурсию:
    // GroupsService groupsService = new GroupsService();

    // Метод для получения всех групп по руководителю
    public List<Groups> getGroupsbyRykovoditel(String rykovoditel)
    {
        return groupsDAO.findByRykovoditel(rykovoditel); // Получаем группы по руководителю
    }

    // Метод для получения всех групп по руководителю
    public List<Groups> getGroupsbynumbergroups(String numbergroups)
    {
        return groupsDAO.findBynumbergroups(numbergroups); // Получаем группы по руководителю
    }




    // Метод для удаления группы по ФИО
    public boolean deletegroupbyfio(String FIO) {
        return groupsDAO.deleteGroupByFIO(FIO);
    }

    // Метод для обновления информации о пользователе по ФИО
    public boolean UpadateUseringroupsByfio(String fio, String number_groups, String rykovoditel) {
        // Создаем новый объект группы
        Groups newUser2 = new Groups();
        newUser2.setFIO_(fio);
        newUser2.setGroup_number(number_groups);
        newUser2.setRyk(rykovoditel);

        // Обновляем информацию о пользователе через DAO
        return groupsDAO.updateuseringroupsbyfio(newUser2);  // Метод для обновления
    }

    // Метод для добавления нового клиента в группу
    public boolean add_new_client_in_group(String fio, String groupNumber, String ryk) {
        // Создаем объект группы
        Groups newGroup = new Groups();
        newGroup.setFIO_(fio);  // Устанавливаем ФИО
        newGroup.setGroup_number(groupNumber);  // Устанавливаем номер группы
        newGroup.setRyk(ryk);  // Устанавливаем руководителя

        System.out.println("-------");
        System.out.println(newGroup.getRyk());
        System.out.println(newGroup.getGroup_number());
        System.out.println(newGroup.getFIO_());

        // Проверяем, существует ли уже такой пользователь с таким же ФИО, номером группы и руководителем
        return groupsDAO.addNewClientToGroup(newGroup);
        // Если такая группа уже существует, возвращаем false
    }
}
