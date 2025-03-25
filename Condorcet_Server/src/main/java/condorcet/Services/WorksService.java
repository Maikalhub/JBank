package condorcet.Services;

import condorcet.DataAccessObjects.WorksDAO;
import condorcet.Models.Entities.Client;
import condorcet.Models.Entities.Groups;
import condorcet.Models.Entities.Works;

import java.util.List;

public class WorksService
{
    Client client;
    //WorksService worksService;

    WorksDAO worksDAO = new WorksDAO();


    // Метод для получения всех администраторов
    public List<Works> getGroupsbyRykovoditel(String rykovoditel)
    {
        return worksDAO.findByRykovoditel(rykovoditel); // Получаем всех администраторов
    }

    // Метод для получения всех администраторов
    public List<Works> getGroupsbyRykovoditel2(String fio)
    {
        return worksDAO.findByRykovoditel2(fio); // Получаем всех администраторов
    }





    // Метод для удаления группы по ФИО
    public boolean deletegroupbyfio(String FIO) {
        return worksDAO.deleteWorksByFIO(FIO);
    }




    public boolean add_new_client_in_work(String taskFio, String numberGroups, String ryk, String works, String statusWorks, String acceptedTime, String checkTime)
    {
        // Создаем объект группы
        Works work = new Works();
        work.setTask_FIO(taskFio);
        work.setNumber_groups(numberGroups);
        work.setRyk_(ryk);
        work.setWorks(works);
        work.setStatus_works(statusWorks);
        work.setAccepted_time(acceptedTime);
        work.setCheck_time(checkTime);



        // Проверяем, существует ли уже такой пользователь с таким же ФИО, номером группы и руководителем
        return worksDAO.addNewClientToWork(work);
    }

    public boolean UpadateUserinWorksByfio(Works newWork) {
        // Используем переданный объект Works для обновления
        return worksDAO.updateuseringroupsbyfio(newWork);  // Метод для обновления в DAO
    }



}
