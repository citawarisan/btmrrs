<%-- 
    Document   : reservation
    Created on : Jun 2, 2023, 11:18:52 PM
    Author     : Omar Alomory(S63955)
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reserve</title>
    </head>
    <style>
        *{
            padding: 0;
            margin: 0;
            box-sizing: border-box;
        }
        .reverseBody{
            background-image: url("https://wallpaperaccess.com/full/4685960.jpg");
            width: 100%;
            height: 100vh;
        }
        .reserveContent{
            display: flex;
            width: 90%;
            height: 80vh;

            flex-direction: row-reverse;
            flex-wrap: wrap;
            align-content: space-around;
            align-items: flex-end;
            justify-content: center;

        }
        .reserveLeft{
            width: 30%;
        }
        .reserveRight{
            width: 70%
        }

        th, td {
            text-align: center;
            vertical-align: middle;
        }

        :root{
            --today: rgba(30, 136, 229, 0.5);
        }

        .today {
            background-color: var(--today);
        }

        .past {
            background-color: rgba(0, 0, 0, 0.25);
        }

        .clickable {
            cursor: pointer;
        }

        .clickable:hover {
            background-color: rgba(124, 179, 66, 0.5);
        }
    </style>
</head>
<body>

    <%@include file="comp/nav.jsp" %>
    <div class="reserveBody">
        <div class="reserveContent">
            <div class="contentLeft contentAll">

            </div>
            <div class="contentRight contentAll">
                <table class="calendar">
                    <thead>
                        <tr>
                            <th colspan="2" id="left" class="clickable" title="Previous month">&lt;</th>
                            <th colspan="3" id="month" class="clickable" title="Back to current month"></th>
                            <th colspan="2" id="right" class="clickable" title="Next month">&gt;</th>
                        </tr>
                    </thead>
                    <thead>
                        <tr>
                            <th>Sunday</th>
                            <th>Monday</th>
                            <th>Tuesday</th>
                            <th>Wednesday</th>
                            <th>Thursday</th>
                            <th>Friday</th>
                            <th>Saturday</th>
                        </tr>
                    </thead>
                    <tbody></tbody>
                </table>
            </div>
        </div>
    </div>
    <%@include file="comp/footer.jsp" %> 

   <script>
    document.addEventListener('DOMContentLoaded', function() {
      let date = new Date();
      let tempDate = date;

      let calendar = document.querySelector('.calendar');
      let monthHeader = calendar.querySelector('#month');
      let calendarBody = calendar.querySelector('tbody');

      function renderCalendar(month) {
        date = new Date();
        tempDate.setMonth(tempDate.getMonth() + (month || 0));
        let y = tempDate.getFullYear(),
          m = tempDate.getMonth();
        let firstDay = new Date(y, m, 1).getDay(),
          lastDay = new Date(y, m + 1, 0).getDate();

        // Set the month and year in the header
        monthHeader.innerText = tempDate.toLocaleString('default', {
          month: 'long',
          year: 'numeric'
        });

        // Loop through the weeks of the month
        calendarBody.innerHTML = '';

        let day = 1;
        while (day <= lastDay) {
          let tr = document.createElement('tr');

          // Loop through the days of the week
          for (let i = 0; i < 7; i++) {
            let td = document.createElement('td');
            if (firstDay > 0) {
              firstDay--;
            } else if (day <= lastDay) {
              td.innerText = day.toString();

              let renderDate = new Date(y, m, day);

              if (renderDate.toDateString() === date.toDateString())
                td.classList.add('today');
              else if (renderDate > date)
                td.classList.add('clickable');
            else{
                td.classList.add('past');
            }

              day++;
            }

            tr.appendChild(td);
          }

          calendarBody.appendChild(tr);
        }
      }

      renderCalendar();

      let previousButton = calendar.querySelector('#left');
      let nextButton = calendar.querySelector('#right');

      monthHeader.addEventListener('click', function() {
        tempDate = date;
        renderCalendar();
      });

      // Event handler for the previous button
      previousButton.addEventListener('click', function() {
        renderCalendar(-1);
      });

      // Event handler for the next button
      nextButton.addEventListener('click', function() {
        renderCalendar(1);
      });

      // Handle link click event
      document.addEventListener('click', function(event) {
        if (event.target.classList.contains('clickable')) {
          let day = event.target.innerText; // Get the day value from the clicked cell
          showTimeInterval(day); // Call the showTimeInterval method with the day
        }
      });

      // Method to show the time interval
      function showTimeInterval(day) {
        // Do whatever you want with the day parameter
        console.log('Clicked day:', day);
        // Your code here...
      }
    });
  </script>
</body>
</html>
