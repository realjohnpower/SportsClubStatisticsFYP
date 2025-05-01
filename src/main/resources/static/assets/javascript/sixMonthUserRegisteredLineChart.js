function displayUserRegisteredLineChart(data) {
    //Setting dimensions for the line graph.
    var margin = { top: 50, right: 30, bottom: 70, left: 60 },
        width = 1200 - margin.left - margin.right,
        height = 400 - margin.top - margin.bottom;


    const tooltip = d3.select("body").append("div")
        .attr("class", "tooltip")




    // Appending svg to the user-registered-line-chart div
    var svg = d3.select("#user-registered-line-chart")
        .append("svg")
        .attr("width", width + margin.left + margin.right)
        .attr("height", height + margin.top + margin.bottom)
        .append("g")
        .attr("transform", `translate(${margin.left},${margin.top})`);

    // Parsing date values to display only year and month
    var parseDate = d3.timeParse("%Y-%m");
    data.forEach(d => d.date = parseDate(d.date));

    // Creating x-axis scales for the line graph
    var x = d3.scaleTime()
        .domain(d3.extent(data, d => d.date))
        .range([0, width]);
    // Creating y-axis scales for the line graph
    var y = d3.scaleLinear()
        .domain([0, d3.max(data, d => d.count)])
        .nice()
        .range([height, 0]);

    // Adding X axis to the line graph
    svg.append("g")
        .attr("transform", `translate(0,${height})`)
        .call(d3.axisBottom(x).ticks(d3.timeMonth).tickFormat(d3.timeFormat("%b %Y")))
        .selectAll("text")
        .attr("transform", "rotate(-45)")
        .style("text-anchor", "end");

    // Adding Y axis to the line graph
    svg.append("g")
        .call(d3.axisLeft(y));

    // Creating the line for the line graph
    var line = d3.line()
        .x(d => x(d.date))
        .y(d => y(d.count))

    // Adding the line path to the line graph
    svg.append("path")
        .datum(data)
        .attr("fill", "none")
        .attr("stroke", "steelblue")
        .attr("stroke-width", 2)
        .attr("d", line);

const tooltipTimeFormat=d3.timeFormat("%B %Y");
    // Adding circles for the line graph according to the data passed in
    svg.selectAll("circle")
        .data(data)
        .join("circle")
        .attr("cx", d => x(d.date))
        .attr("cy", d => y(d.count))
        .attr("r", 5)
        .attr("fill", "red")
        .on("mouseover", (event, d) => {
            tooltip.style("opacity", 1)
                .html(`<strong>Date: ${tooltipTimeFormat(d.date)}</strong><br>New Members Total: ${d.count}`)
                .style("left", (event.pageX + 15) + "px")
                .style("top", (event.pageY + 15) + "px");
        })
        .on("mouseout", () => tooltip.style("opacity", 0));


    //Adding chart title to the line graph
    svg.append("text")
        .attr("x", width / 2)
        .attr("y", -20)
        .attr("text-anchor", "middle")
        .style("font-size", "16px")
        .style("font-weight", "bold")
        .text("Number of New Members Over The Past Six Months");

    // Adding X label to the line graph
    svg.append("text")
        .attr("x", width / 2)
        .attr("y", height + 70)
        .attr("text-anchor", "middle")
        .style("font-size", "14px")
        .text("Months");

    // Adding Y label to the line graph
    svg.append("text")
        .attr("transform", "rotate(-90)")
        .attr("x", -height / 2)
        .attr("y", -40)
        .attr("text-anchor", "middle")
        .style("font-size", "14px")
        .text("Number of New Members");
}
