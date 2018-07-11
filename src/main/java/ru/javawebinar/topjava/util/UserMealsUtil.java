package ru.javawebinar.topjava.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

public class UserMealsUtil {

  public static void main(String[] args) {
    List<UserMeal> mealList = Arrays.asList(
        new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
        new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
        new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
        new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
        new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
        new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
    );
    getFilteredWithExceeded(mealList, LocalTime.of(0, 0), LocalTime.of(23, 0), 2000)
        .forEach(m-> System.out.println(m.getDateTime()+ " " + m.isExceed()));
//        .toLocalDate();
//        .toLocalTime();
  }

  public static List<UserMealWithExceed> getFilteredWithExceeded(List<UserMeal> mealList,
      LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
    Map<LocalDate, Integer> dayCaloriesMap = mealList.stream()
        .collect(Collectors.groupingBy(
            m -> m.getDateTime().toLocalDate(),
            Collectors.summingInt(m -> m.getCalories())));
    return mealList.stream().filter(m -> m.getDateTime().toLocalTime().isBefore(endTime)
        && m.getDateTime().toLocalTime().isAfter(startTime))
        .map(m -> new UserMealWithExceed(m.getDateTime(), m.getDescription(), m.getCalories(),
            dayCaloriesMap.get(m.getDateTime().toLocalDate()) > caloriesPerDay))
        .collect(Collectors.toList());
    // TODO return filtered list with correctly exceeded field
  }


}
