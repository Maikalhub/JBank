package condorcet.Utility;

import com.google.gson.Gson;
import condorcet.Enums.ResponseStatus;
import condorcet.Models.Entities.*;
import condorcet.Models.TCP.Request;
import condorcet.Models.TCP.Response;
import condorcet.Services.*;
import org.hibernate.tuple.CreationTimestampGeneration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class ClientThread implements Runnable {

    private Socket clientSocket;
    private Request request;
    private Response response;
    private Gson gson;
    private BufferedReader in;
    private PrintWriter out;

    private AdminAService adminAService = new AdminAService(); // Service для Admin_A
    private UserAService userAService = new UserAService(); // Service для User_A
    private AdminIDService adminIDService = new AdminIDService();
    private UserIDService userIDService = new UserIDService();
    private GroupsService groupsService = new GroupsService();
    private WorksService worksService = new WorksService();
    private ArchiveService archiveService = new ArchiveService();



    public ClientThread(Socket clientSocket) throws IOException
    {
        response = new Response();
        request = new Request();
        this.clientSocket = clientSocket;
        gson = new Gson();
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        out = new PrintWriter(clientSocket.getOutputStream());
    }

    @Override
    public void run() {
        try {
            while (clientSocket.isConnected()) {
                String message = in.readLine();
                if (message == null) {
                    continue; // Skip empty or null messages
                }

                request = gson.fromJson(message, Request.class);

                switch (request.getRequestType())
                {
                    case REGISTER:
                        // Handle registration logic here (you might need a registration service)
                        break;

                    case LOGIN:
                        System.out.println("Запрос на авторизацию от клиента.");
                        Client requestClient = gson.fromJson(request.getRequestMessage(), Client.class);
                        System.out.println(requestClient);
                        System.out.println("Обрабатываем данные на выборку...");

                        System.out.println(requestClient.getLogin());
                        System.out.println(requestClient.getPassword());


                        UserA user = userAService.findByLoginAndPassword(requestClient.getLogin(), requestClient.getPassword());
                        AdminA admin = adminAService.findByLoginAndPassword(requestClient.getLogin(), requestClient.getPassword());

                        if (user != null)
                        {

                            response = new Response(ResponseStatus.OK, "Добро пожаловать, пользователь!", gson.toJson(user));
                        } else if (admin != null) {
                            response = new Response(ResponseStatus.OK, "Добро пожаловать, администратор!", gson.toJson(admin));
                        } else {
                            response = new Response(ResponseStatus.ERROR, "Такого пользователя не существует или неправильный пароль!", "");
                        }
                        break;

                    case SELECT_ADMINA:

                        System.out.println("Запрос на выборку данных из таблицы AdminA от клиента.");
                        System.out.println(gson.toJson(request));  // Логирование запроса в формате JSON

                        System.out.println("Обрабатываем данные на выборку...");

                        // В данном случае, нам не нужно обновлять данные, а только получить все данные
                        List<AdminA> allAdmins = adminAService.getAllAdmins();  // Получаем всех администраторов из базы
                        // Логирование выборки данных
                        System.out.println("Список всех администраторов:");
                        for (AdminA admins : allAdmins)
                        {
                            System.out.println("ID: " + admins.getId() + ", Логин: " + admins.getLogin() + ", Пароль: " + admins.getPassword());
                        }

                        // Преобразуем список администраторов в JSON
                        String allAdminsJson = gson.toJson(allAdmins);
                        System.out.println(gson.toJson(allAdmins));

                        // Формируем ответ с выбранными данными
                        response = new Response (ResponseStatus.OK, "Данные успешно выбраны!", allAdminsJson);

                        // Логирование отправляемого ответа
                        //System.out.println("Отправляемый ответ:");
                        //System.out.println(gson.toJson(response));

                        // Выводим сформированный ответ
                        //System.out.println("Отправляемый ответ:");
                        //System.out.println(gson.toJson(response));
                        break;

                    case SELECT_GROUP:
                        System.out.println("Запрос на выборку данных из таблицы Groups от клиента:");
                        System.out.println(gson.toJson(request));  // Логирование запроса в формате JSON
                        // Извлекаем объект Client из запроса
                        Client requestClient2 = gson.fromJson(request.getRequestMessage(), Client.class);
                       //System.out.println(requestClient2);
                        // Логин клиента передаем в метод для получения групп
                        String clientLogin = requestClient2.getLogin();  // Извлекаем логин клиента из объекта
                        System.out.println("Десериализация данных:");
                        System.out.println(gson.toJson(requestClient2.getLogin()));
                        System.out.println("Обрабатываем данные на выборку...");

                        // Вызываем метод getGroupsbyRykovoditel, передавая логин клиента
                        List<Groups> groupsByRykovoditel = groupsService.getGroupsbyRykovoditel(clientLogin);

                        // Логирование выборки данных
                        System.out.println("Список групп для руководителя с логином " + clientLogin + ":");
                        for (Groups group : groupsByRykovoditel) {
                            System.out.println("ID: " + group.getGroup_Id() + ", НомерГруппы: " + group.getGroup_number() + " , Руководитель: " + group.getRyk());
                        }

                        // Преобразуем список в JSON и формируем ответ
                        String groupsJson = gson.toJson(groupsByRykovoditel);
                        response = new Response(ResponseStatus.OK, "Данные успешно выбраны!", groupsJson);
                        System.out.println("Данные отправлены клиенту:");
                        // Логирование отправляемого ответа
                        System.out.println(gson.toJson(response));
                        break;

                    case SELECT_GROUP_USER:
                        System.out.println("Запрос на выборку данных из таблицы Groups от клиента:");
                        System.out.println(gson.toJson(request));  // Логирование запроса в формате JSON
                        // Извлекаем объект Client из запроса
                        Client requestClient44 = gson.fromJson(request.getRequestMessage(), Client.class);
                        //System.out.println(requestClient2);
                        // Логин клиента передаем в метод для получения групп
                        String clientLogin47 = requestClient44.getPassword();  // Извлекаем логин клиента из объекта
                        System.out.println("Десериализация данных:");
                        System.out.println(gson.toJson(requestClient44.getPassword()));
                        System.out.println("Обрабатываем данные на выборку...");
                        // Вызываем метод getGroupsbyRykovoditel, передавая логин клиента
                        List<Groups> groupsBynumbergroups = groupsService.getGroupsbynumbergroups(clientLogin47);
                        // Логирование выборки данных
                        System.out.println("Список групп для пользователя с логином " + clientLogin47 + ":");
                        for (Groups group : groupsBynumbergroups)
                        {
                            System.out.println("ID: " + group.getGroup_Id() + ", НомерГруппы: " + group.getGroup_number() + " , Руководитель: " + group.getRyk());
                        }

                        // Преобразуем список в JSON и формируем ответ
                        String groupsJson22 = gson.toJson(groupsBynumbergroups);
                        response = new Response(ResponseStatus.OK, "Данные успешно выбраны!", groupsJson22);
                        System.out.println("Данные отправлены клиенту:");
                        // Логирование отправляемого ответа
                        System.out.println(gson.toJson(response));
                        break;
                    case SELECT_WORKS_USER:
                        System.out.println("Запрос на выборку данных из таблицы Works от клиента:");
                        // Извлекаем объект Client из запроса
                        Client requestClient355 = gson.fromJson(request.getRequestMessage(), Client.class);
                        System.out.println(requestClient355);
                        System.out.println(gson.toJson(request));  // Логирование запроса в формате JSON
                        // Логин клиента передаем в метод для получения групп
                        String clientLogin27 = requestClient355.getLogin();  // Извлекаем логин клиента из объекта
                        System.out.println("Десериализация данных:");
                        System.out.println(gson.toJson(requestClient355.getLogin()));
                        System.out.println("Обрабатываем данные на выборку...");

                        // Вызываем метод getGroupsbyRykovoditel, передавая логин клиента
                        List<Works> worksByRykovoditel77 = worksService.getGroupsbyRykovoditel2(clientLogin27);

                        // Логирование выборки данных
                        System.out.println("Список групп для руководителя с логином " + clientLogin27 + ":");
                        for (Works works : worksByRykovoditel77)
                        {
                            System.out.print(
                                    "ID: " + works.getTask_Id() + ", ФИО: " + works.getTask_FIO() +
                                            ", НомерГруппы: " + works.getTask_FIO() +
                                            " Руководитель: " + works.getRyk_() +
                                            ", Работа: " + works.getWorks() + ", СтатусРаботы: " + works.getStatus_works() +
                                            ", Принято: " + works.getAccepted_time() + ", Проверено: " + works.getCheck_time());}

                        // Преобразуем список в JSON и формируем ответ
                        String groupsJson2 = gson.toJson(worksByRykovoditel77);
                        response = new Response(ResponseStatus.OK, "Данные успешно выбраны!", groupsJson2);
                        System.out.println("Данные отправлены клиенту:");
                        // Логирование отправляемого ответа
                        System.out.println(gson.toJson(response));
                        break;
                    case SELECT_WORKS:
                        System.out.println("Запрос на выборку данных из таблицы Works от клиента:");
                        // Извлекаем объект Client из запроса
                        Client requestClient3 = gson.fromJson(request.getRequestMessage(), Client.class);
                        System.out.println(requestClient3);
                        System.out.println(gson.toJson(request));  // Логирование запроса в формате JSON
                        // Логин клиента передаем в метод для получения групп
                        String clientLogin2 = requestClient3.getLogin();  // Извлекаем логин клиента из объекта
                        System.out.println("Десериализация данных:");
                        System.out.println(gson.toJson(requestClient3.getLogin()));
                        System.out.println("Обрабатываем данные на выборку...");

                        // Вызываем метод getGroupsbyRykovoditel, передавая логин клиента
                        List<Works> worksByRykovoditel = worksService.getGroupsbyRykovoditel(clientLogin2);

                        // Логирование выборки данных
                        System.out.println("Список групп для руководителя с логином " + clientLogin2 + ":");
                        for (Works works : worksByRykovoditel)
                        {
                            System.out.print(
                                    "ID: " + works.getTask_Id() + ", ФИО: " + works.getTask_FIO() +
                                    ", НомерГруппы: " + works.getTask_FIO() +
                                    " Руководитель: " + works.getRyk_() +
                                    ", Работа: " + works.getWorks() + ", СтатусРаботы: " + works.getStatus_works() +
                                    ", Принято: " + works.getAccepted_time() + ", Проверено: " + works.getCheck_time());}

                        // Преобразуем список в JSON и формируем ответ
                        String groupsJson772 = gson.toJson(worksByRykovoditel);
                        response = new Response(ResponseStatus.OK, "Данные успешно выбраны!", groupsJson772);
                        System.out.println("Данные отправлены клиенту:");
                        // Логирование отправляемого ответа
                        System.out.println(gson.toJson(response));
                        break;
                    case SELECT_ARCHIVE:
                        System.out.println("Запрос на выборку данных из таблицы Archive от клиента:");
                        // Извлекаем объект Client из запроса
                        Client requestClient4 = gson.fromJson(request.getRequestMessage(), Client.class);
                        System.out.println(requestClient4);
                        System.out.println(gson.toJson(request));  // Логирование запроса в формате JSON
                        // Логин клиента передаем в метод для получения групп
                        String clientLogin4 = requestClient4.getLogin();  // Извлекаем логин клиента из объекта
                        System.out.println("Десериализация данных:");
                        System.out.println(gson.toJson(requestClient4.getLogin()));
                        System.out.println("Обрабатываем данные на выборку...");
                        // Вызываем метод getGroupsbyRykovoditel, передавая логин клиента
                        List<Archive> archiveByRykovoditel77 = archiveService.getGroupsbyRykovoditel(clientLogin4);
                        // Логирование выборки данных
                        System.out.println("Список групп для руководителя с логином " + clientLogin4 + ":");
                        for (Archive archive : archiveByRykovoditel77)
                        {
                            System.out.print(
                                    "ID: " + archive.getTask_Id() + ", ФИО: " + archive.getTask_FIO() +
                                            ", НомерГруппы: " + archive.getTask_FIO() +
                                            " Руководитель: " + archive.getRyk_() +
                                            ", Работа: " + archive.getWorks() + ", СтатусРаботы: " + archive.getStatus_works() +
                                            ", Принято: " + archive.getAccepted_time() + ", Проверено: " + archive.getCheck_time());
                        }

                        // Преобразуем список в JSON и формируем ответ
                        String groupsJson3 = gson.toJson(archiveByRykovoditel77);
                        response = new Response(ResponseStatus.OK, "Данные успешно выбраны!", groupsJson3);
                        System.out.println("Данные отправлены клиенту:");
                        // Логирование отправляемого ответа
                        System.out.println(gson.toJson(response));
                        break;
                    case SELECT_ARCHIVE_USER:

                        System.out.println("Запрос на выборку данных из таблицы Archive от клиента:");
                        // Извлекаем объект Client из запроса
                        Client requestClient48 = gson.fromJson(request.getRequestMessage(), Client.class);
                        System.out.println(requestClient48);
                        System.out.println(gson.toJson(request));  // Логирование запроса в формате JSON
                        // Логин клиента передаем в метод для получения групп
                        String clientLogin66 = requestClient48.getLogin();  // Извлекаем логин клиента из объекта
                        System.out.println("Десериализация данных:");
                        System.out.println(gson.toJson(requestClient48.getLogin()));
                        System.out.println("Обрабатываем данные на выборку...");
                        // Вызываем метод getGroupsbyRykovoditel, передавая логин клиента
                        List<Archive> archiveByRykovoditel779 = archiveService.getGroupsbyFio(clientLogin66);
                        // Логирование выборки данных
                        System.out.println("Список групп для руководителя с логином " + clientLogin66 + ":");
                        for (Archive archive : archiveByRykovoditel779)
                        {
                            System.out.print(
                                    "ID: " + archive.getTask_Id() + ", ФИО: " + archive.getTask_FIO() +
                                            ", НомерГруппы: " + archive.getTask_FIO() +
                                            " Руководитель: " + archive.getRyk_() +
                                            ", Работа: " + archive.getWorks() + ", СтатусРаботы: " + archive.getStatus_works() +
                                            ", Принято: " + archive.getAccepted_time() + ", Проверено: " + archive.getCheck_time());
                        }

                        // Преобразуем список в JSON и формируем ответ
                        String groupsJson36 = gson.toJson(archiveByRykovoditel779);
                        response = new Response(ResponseStatus.OK, "Данные успешно выбраны!", groupsJson36);
                        System.out.println("Данные отправлены клиенту:");
                        // Логирование отправляемого ответа
                        System.out.println(gson.toJson(response));
                        break;
                    case ADD_USERA:
                        System.out.println("Запрос на добавление в таблицу UserA от клиента:");
                        Client requestClient7 = gson.fromJson(request.getRequestMessage(), Client.class);
                        System.out.println(requestClient7);

                        boolean user5 = userAService.addUserA(requestClient7.getLogin(), requestClient7.getPassword());

                        if (user5)
                        {
                            response = new Response(ResponseStatus.OK, "Данные успешно добавлены!", gson.toJson(user5));
                        }
                        else
                        {
                            response = new Response(ResponseStatus.ERROR, "Такого пользователя не существует или неправильный пароль!", "");
                        }
                        break;

                    case ADD_USERID:
                        System.out.println("Запрос на добавление в таблицу UserID от клиента:");

                        UserID requestClient8= gson.fromJson(request.getRequestMessage(), UserID.class);
                        System.out.println(requestClient8);

                        boolean user9 = userIDService.addUserID(requestClient8.getLogin(), requestClient8.getPassword(), requestClient8.getFio(), requestClient8.getRole(), requestClient8.getStatus(), requestClient8.getSalary(), requestClient8.getCurrency(), requestClient8.getGroups());

                        if (user9)
                        {
                            response = new Response(ResponseStatus.OK, "Данные успешно добавлены!", gson.toJson(user9));
                        }
                        else
                        {
                            response = new Response(ResponseStatus.ERROR, "Такого пользователя не существует или неправильный пароль!", "");
                        }
                        break;
                    case ADD_GROUP:
                        System.out.println("Запрос на добавление в таблицу Group от клиента:");

                        Groups requestClient80= gson.fromJson(request.getRequestMessage(), Groups.class);
                        System.out.println(requestClient80);

                        boolean user90 = groupsService.add_new_client_in_group(requestClient80.getFIO_(), requestClient80.getGroup_number(), requestClient80.getRyk());

                        if (user90)
                        {
                            response = new Response(ResponseStatus.OK, "Данные успешно добавлены!", gson.toJson(user90));
                        }
                        else
                        {
                            response = new Response(ResponseStatus.ERROR, "Такого пользователя не существует или неправильный пароль!", "");
                        }
                        break;
                    case ADD_WORKS:
                        System.out.println("Запрос на добавление в таблицу Works от клиента:");

                        Works requestClient800 = gson.fromJson(request.getRequestMessage(), Works.class);
                        System.out.println(requestClient800);

                        boolean user900 = worksService.add_new_client_in_work(requestClient800.getTask_FIO(), requestClient800.getNumber_groups(), requestClient800.getRyk_(), requestClient800.getWorks(), requestClient800.getStatus_works(), requestClient800.getAccepted_time(), requestClient800.getCheck_time());

                        if (user900)
                        {
                            response = new Response(ResponseStatus.OK, "Данные успешно добавлены!", gson.toJson(user900));
                        }
                        else
                        {
                            response = new Response(ResponseStatus.ERROR, "Такого пользователя не существует или неправильный пароль!", "");
                        }
                        break;
                    case ADD_ARCHIVE:
                        System.out.println("Запрос на добавление в таблицу Works от клиента:");

                        Archive requestClient8000 = gson.fromJson(request.getRequestMessage(), Archive.class);
                        System.out.println(requestClient8000);

                        boolean user9000 = archiveService.add_new_client_in_work(requestClient8000.getTask_FIO(), requestClient8000.getNumber_groups(), requestClient8000.getRyk_(), requestClient8000.getWorks(), requestClient8000.getStatus_works(), requestClient8000.getAccepted_time(), requestClient8000.getCheck_time());

                        if (user9000)
                        {
                            response = new Response(ResponseStatus.OK, "Данные успешно добавлены!", gson.toJson(user9000));
                        }
                        else
                        {
                            response = new Response(ResponseStatus.ERROR, "Такого пользователя не существует или неправильный пароль!", "");
                        }
                        break;
                    case EDIT_USERA:
                        break;
                    case EDIT_USERID:
                        System.out.println("Запрос на изменение в таблице UserID от клиента:");
                        UserID requestClient10 = gson.fromJson(request.getRequestMessage(), UserID.class);
                        System.out.println(requestClient10);

                        boolean user10 = userIDService.UpadateuserBylogin(requestClient10.getLogin(), requestClient10.getPassword(), requestClient10.getFio(), requestClient10.getRole(), requestClient10.getStatus(), requestClient10.getSalary(), requestClient10.getCurrency(), requestClient10.getGroups());

                        if (user10)
                        {
                            response = new Response(ResponseStatus.OK, "Данные успешно изменены !", gson.toJson(user10));
                        }
                        else
                        {
                            response = new Response(ResponseStatus.ERROR, "Данные не изменены!", "");
                        }
                        break;
                    case EDIT_USERID2:
                        System.out.println("Запрос на изменение в таблице UserID от клиента:");
                        UserID requestClient100 = gson.fromJson(request.getRequestMessage(), UserID.class);
                        System.out.println(requestClient100);

                        boolean user100 = userIDService.UpadateuserBylogin2(requestClient100.getFio(), requestClient100.getGroups());

                        if (user100)
                        {
                            response = new Response(ResponseStatus.OK, "Данные успешно изменены !", gson.toJson(user100));
                        }
                        else
                        {
                            response = new Response(ResponseStatus.ERROR, "Данные не изменены!", "");
                        }
                        break;
                    case EDIT_ADMINA:
                        break;
                    case EDIT_ADMINID:
                        System.out.println("Запрос на изменение в таблице AdminID от клиента:");

                        AdminID requestClient11 = gson.fromJson(request.getRequestMessage(), AdminID.class);
                        System.out.println(requestClient11);

                        System.out.println(requestClient11.getSalary());

                        boolean user11 = adminIDService.UpadateadminBylogin(requestClient11.getLogin(), requestClient11.getPassword(), requestClient11.getFio(), requestClient11.getRole(), requestClient11.getStatus(), requestClient11.getSalary(), requestClient11.getCurrency(), requestClient11.getGroups());

                        if (user11)
                        {
                            response = new Response(ResponseStatus.OK, "Данные успешно изменены !", gson.toJson(user11));
                        }
                        else
                        {
                            response = new Response(ResponseStatus.ERROR, "Данные не изменены!", "");
                        }
                        break;
                    case EDIT_GROUP:
                        System.out.println("Запрос на изменение в таблице Groups от клиента:");

                        Groups requestClient12 = gson.fromJson(request.getRequestMessage(), Groups.class);
                        System.out.println(requestClient12);

                        boolean user12 = groupsService.UpadateUseringroupsByfio(requestClient12.getFIO_(), requestClient12.getGroup_number(), requestClient12.getRyk());

                        if (user12)
                        {
                            response = new Response(ResponseStatus.OK, "Данные успешно изменены !", gson.toJson(user12));
                        }
                        else
                        {
                            response = new Response(ResponseStatus.ERROR, "Данные не изменены!", "");
                        }
                        break;
                    case EDIT_GROUP3:
                        System.out.println("Запрос на изменение в таблице Works от клиента:");

                        // Преобразуем строку JSON в объект Works
                        Works requestClient121 = gson.fromJson(request.getRequestMessage(), Works.class);
                        System.out.println(requestClient121);  // Выводим объект для отладки
                        // Обновляем данные в базе данных
                        boolean user112 = worksService.UpadateUserinWorksByfio(requestClient121);
                        // Подготовка ответа
                        if (user112) {
                            response = new Response(ResponseStatus.OK, "Данные успешно изменены!", gson.toJson(user112));
                        } else {
                            response = new Response(ResponseStatus.ERROR, "Данные не изменены!", "");
                        }
                        break;
                    case EDIT_WORKS:
                        /*
                        System.out.println("Запрос на изменение в таблице Works от клиента:");

                        Works requestClient13 = gson.fromJson(request.getRequestMessage(), Works.class);
                        System.out.println(requestClient13);

                        boolean user13 = worksService(requestClient13);

                        if (user13)
                        {
                            response = new Response(ResponseStatus.OK, "Данные успешно изменены !", gson.toJson(user13));
                        }
                        else
                        {
                            response = new Response(ResponseStatus.ERROR, "Данные не изменены!", "");
                        }

                         */
                        break;
                    case DELETE_USERA:
                        break;
                    case DELETE_GROUP:
                        System.out.println("Запрос на удаление в таблице Groups от клиента:");

                        Archive requestClient61 = gson.fromJson(request.getRequestMessage(), Archive.class);
                        System.out.println(requestClient61);

                        Boolean groups1111 = archiveService.deletegroupbyfio(requestClient61.getTask_FIO());
                        if (groups1111 != null)
                        {
                            response = new Response(ResponseStatus.OK, "Данные успешно удалены Archive !", gson.toJson(groups1111));
                        }
                        else
                        {
                            response = new Response(ResponseStatus.ERROR, "Такого пользователя не существует или неправильный пароль!", "");
                        }

                        Works requestClient51 = gson.fromJson(request.getRequestMessage(), Works.class);
                        System.out.println(requestClient51);

                        Boolean groups111 = worksService.deletegroupbyfio(requestClient51.getTask_FIO());
                        if (groups111 != null)
                        {
                            response = new Response(ResponseStatus.OK, "Данные успешно удалены Work !", gson.toJson(groups111));
                        }
                        else
                        {
                            response = new Response(ResponseStatus.ERROR, "Такого пользователя не существует или неправильный пароль!", "");
                        }


                        Groups requestClient5 = gson.fromJson(request.getRequestMessage(), Groups.class);
                        System.out.println(requestClient5);

                        Boolean groups = groupsService.deletegroupbyfio(requestClient5.getFIO_());
                        if (groups != null) {
                            response = new Response(ResponseStatus.OK, "Данные успешно удалены Group !", gson.toJson(groups));
                        }
                        else
                        {
                            response = new Response(ResponseStatus.ERROR, "Такого пользователя не существует или неправильный пароль!", "");
                        }
                        break;

                    case DELETE_WORKS:
                        System.out.println("Запрос на удаление в таблице Works от клиента:");
                        Works requestClient55 = gson.fromJson(request.getRequestMessage(), Works.class);
                        System.out.println(requestClient55);

                        Boolean m = worksService.deletegroupbyfio(requestClient55.getTask_FIO());
                        if (m != null) {
                            response = new Response(ResponseStatus.OK, "Данные успешно удалены !", gson.toJson(m));
                        }
                        else
                        {
                            response = new Response(ResponseStatus.ERROR, "Такого пользователя не существует или неправильный пароль!", "");
                        }
                        break;
                    case DELETE_ARCHIVE:
                        break;

                    default:
                        response = new Response(ResponseStatus.ERROR, "Неизвестный запрос", "");
                        break;
                }

                out.println(gson.toJson(response));
                out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
            response = new Response(ResponseStatus.ERROR, "Ошибка сервера: " + e.getMessage(), "");
            out.println(gson.toJson(response));
            out.flush();
        } finally {
            try {
                clientSocket.close();
                in.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
