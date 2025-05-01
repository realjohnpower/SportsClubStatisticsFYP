

function drawEventStatsBarChart(data) {

    const tooltip = d3.select("body").append("div")
        .attr("class", "tooltip")


    // Setting the dimensions for the graph
    var  margin = {top: 50, right: 30, bottom: 50, left: 60},
        width = 800 - margin.left - margin.right,
        height = 600 - margin.top - margin.bottom;

    // Creating an svg container and appending it to event attendance barchart div.
    var svg = d3.select(".EventAttendance-barchart")
        .append("svg")
        .attr("width", width + margin.left + margin.right)
        .attr("height", height + margin.top + margin.bottom)
        .append("g")
        .attr("transform", `translate(${margin.left},${margin.top})`);

    //  Creating the X-axis scale for the bar graph
    var x = d3.scaleBand()
        .domain(data.map(d => d.category))
        .range([0, width])
        .padding(0.2);

    // Creating the Y-axis scale for the bar graph
    var y = d3.scaleLinear()
        .domain([0, d3.max(data, d => d.value)])
        .range([height, 0]);

    // Adding the x-axis to the bar graph
    svg.append("g")
        .attr("transform", `translate(0,${height})`)
        .call(d3.axisBottom(x));

    // Adding the y-axis to the bar graph
    svg.append("g")
        .call(d3.axisLeft(y));

    // Creating Bars for the bar graph according to the data passed in
    svg.selectAll("rect")
        .data(data)
        .join("rect")
        .attr("x", d => x(d.category))
        .attr("y", d => y(d.value))
        .attr("width", x.bandwidth())
        .attr("height", d => height - y(d.value))
        .attr("fill", "#1f77b4")
        .on("mouseover", (event, d) => {
            tooltip.style("opacity", 1)
                .html(`<strong> ${d.category}</strong><br>Value: ${d.value}`)
                .style("left", (event.pageX + 15) + "px")
                .style("top", (event.pageY + 15) + "px");
        })
        .on("mouseout", () => tooltip.style("opacity", 0));

    // Adding the chart title to the bar graph
    svg.append("text")
        .attr("x", width / 2)
        .attr("y", -20)
        .attr("text-anchor", "middle")
        .style("font-size", "18px")
        .style("font-weight", "bold")
        .text("Distribution Of Club Event Attendance");

    // Adding the x-axis label to the bar graph

    svg.append("text")
        .attr("x", width / 2)
        .attr("y", height + 40)
        .attr("text-anchor", "middle")
        .style("font-size", "14px")
        .text("Attendance Recorded By Users");

    // Adding the  y-axis label to the bar graph
    svg.append("text")
        .attr("transform", "rotate(-90)")
        .attr("x", -height / 2)
        .attr("y", -40)
        .attr("text-anchor", "middle")
        .style("font-size", "14px")
        .text("Number Of Attendance");
}
