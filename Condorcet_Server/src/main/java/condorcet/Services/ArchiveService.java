package condorcet.Services;

import condorcet.DataAccessObjects.ArchiveDAO;
import condorcet.Models.Entities.Archive;
import condorcet.Models.Entities.Client;
import condorcet.Models.Entities.Works;

import java.util.List;

public class ArchiveService
{
    Client client;
    ArchiveService archiveService;

    ArchiveDAO archiveDAO = new ArchiveDAO();



    public ArchiveService getWorksService() {
        return archiveService;
    }

    public void setWorksService(ArchiveService worksService) {
        this.archiveService = worksService;
    }


    // Метод для получения всех администраторов
    public List<Archive> getGroupsbyRykovoditel(String rykovoditel)
    {
        return archiveDAO.findByRykovoditel(rykovoditel); // Получаем всех администраторов
    }

    // Метод для получения всех администраторов
    public List<Archive> getGroupsbyFio(String fio)
    {
        return archiveDAO.findByfio(fio); // Получаем всех администраторов
    }


    // Метод для удаления группы по ФИО
    public boolean deletegroupbyfio(String FIO) {
        return archiveDAO.deleteWorksByFIO(FIO);
    }

    public boolean add_new_client_in_work(String taskFio, String numberGroups, String ryk, String works, String statusWorks, String acceptedTime, String checkTime)
    {
        // Создаем объект группы
        Archive archive = new Archive();
        archive.setTask_FIO(taskFio);
        archive.setNumber_groups(numberGroups);
        archive.setRyk_(ryk);
        archive.setWorks(works);
        archive.setStatus_works(statusWorks);
        archive.setAccepted_time(acceptedTime);
        archive.setCheck_time(checkTime);



        // Проверяем, существует ли уже такой пользователь с таким же ФИО, номером группы и руководителем
        return archiveDAO.addNewClientToWork(archive);
    }



}
