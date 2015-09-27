package ru.nektodev.srg.factorialservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.nektodev.srg.factorialservice.model.Task;
import ru.nektodev.srg.factorialservice.model.TaskQueue;

import java.math.BigInteger;

/**
 * RESTful controller
 *
 * @author Tsykin V.A.
 *         ts.slawa@gmail.com
 * @date 26.09.15
 */
@RestController
public class FactorialController {

    private static TaskQueue queue = TaskQueue.getInstance();

    /**
     * Method to add nuber to task queue
     * @param n number to calculate factorial. If n empty, default value is 0
     * @return "IN WORK" - if successfully add number to queue
     * "ERROR. Cannot calculate negative numbers" - if n < 0
     * @throws NumberFormatException if n not a number
     *
     */
    @RequestMapping("factorial")
    public String factorial(@RequestParam(value = "n", defaultValue = "0") BigInteger n) {
        if (n.compareTo(BigInteger.ZERO) < 0) {
            return "ERROR. Cannot calculate negative numbers";
        }

        queue.addTask(n);

        return "IN WORK";
    }

    /**
     * Method to get results of calculating<br/>
     * String format:<br/>
     * taskId. N! = result &lt;br/><br/>
     * e.g<br/>
     * 5. 10! = 3628800<br/>
     * 6. 10! = 3628800<br/>
     *<br/>
     * Using br tag to easier see it in browser<br/>
     *<br/>
     * If result == null - instead of result return<br/>
     * taskId. N! = вычисляется &lt;br/><br/>
     * e.g.<br/>
     * 15. 0! = вычисляется<br/>
     *<br/>
     * If result == 0 - it means that it was soma errors in calculating^ so returns:<br/>
     * taskId. N! = произошла ошибка &lt;br/><br/>
     * e.g.<br/>
     * 4. -10! = произошла ошибка<br/>
     *<br/>
     * @return string with results<br/>
     */
    @RequestMapping("/")
    public String result() {
        StringBuffer sb = new StringBuffer();
        for (Task task : queue.getResult()) {
            sb.append(task.getId()).append(". ").append(task.getN()).append("! = ");

            if (task.getResult() == null)
                sb.append("вычисляется");
            else if (task.getResult().equals(BigInteger.ZERO))
                sb.append("произошла ошибка");
            else
                sb.append(task.getResult());

            sb.append("<br/>");
        }


        return sb.toString();
    }

}
