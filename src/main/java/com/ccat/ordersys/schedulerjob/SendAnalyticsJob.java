package com.ccat.ordersys.schedulerjob;

import com.ccat.ordersys.model.repository.UserDao;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class SendAnalyticsJob {
    private final UserDao userDao;

    public SendAnalyticsJob(UserDao userDao) {
        this.userDao = userDao;
    }

    @Scheduled(cron="0 0 5 * * ?")
    public void sendAnalytics() {
        //Send amount of User-Accounts created:
        int userAmount = userDao.findAll().size();

        System.out.println("## ---------------- ##");
        System.out.println("Total Amount of created Users: " + userAmount + (userAmount > 1 ? " Users" : " User"));
        System.out.println("## ---------------- ##");
    }
}
