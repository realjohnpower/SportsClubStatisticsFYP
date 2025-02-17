function displayUserRegisteredLineChart(data) {
    var margin = { top: 50, right: 30, bottom: 50, left: 60 },
        width = 800 - margin.left - margin.right,
        height = 400 - margin.top - margin.bottom;

    // Clear previous SVG if any
    d3.select("#user-registered-line-chart").select("svg").remove();

    // Append SVG
    var svg = d3.select("#user-registered-line-chart")
        .append("svg")
        .attr("width", width + margin.left + margin.right)
        .attr("height", height + margin.top + margin.bottom)
        .append("g")
        .attr("transform", `translate(${margin.left},${margin.top})`);

    // Parse date
    var parseDate = d3.timeParse("%Y-%m");
    data.forEach(d => d.date = parseDate(d.date));

    // Scales
    var x = d3.scaleTime()
        .domain(d3.extent(data, d => d.date))
        .range([0, width]);

    var y = d3.scaleLinear()
        .domain([0, d3.max(data, d => d.count)])
        .nice()
        .range([height, 0]);

    // X Axis
    svg.append("g")
        .attr("transform", `translate(0,${height})`)
        .call(d3.axisBottom(x).ticks(d3.timeMonth.every(1)).tickFormat(d3.timeFormat("%b %Y")))
        .selectAll("text")
        .attr("transform", "rotate(-30)")
        .style("text-anchor", "end");

    // Y Axis
    svg.append("g")
        .call(d3.axisLeft(y));

    // Line Generator
    var line = d3.line()
        .x(d => x(d.date))
        .y(d => y(d.count))
        .curve(d3.curveMonotoneX);  // Smooth line

    // Append Line Path
    svg.append("path")
        .datum(data)
        .attr("fill", "none")
        .attr("stroke", "steelblue")
        .attr("stroke-width", 2)
        .attr("d", line);

    // Add Data Points (Circles)
    svg.selectAll("circle")
        .data(data)
        .enter()
        .append("circle")
        .attr("cx", d => x(d.date))
        .attr("cy", d => y(d.count))
        .attr("r", 5)
        .attr("fill", "red");

    // Chart Title
    svg.append("text")
        .attr("x", width / 2)
        .attr("y", -20)
        .attr("text-anchor", "middle")
        .style("font-size", "16px")
        .style("font-weight", "bold")
        .text("User Activity Over Time");

    // X Label
    svg.append("text")
        .attr("x", width / 2)
        .attr("y", height + 40)
        .attr("text-anchor", "middle")
        .style("font-size", "14px")
        .text("Time");

    // Y Label
    svg.append("text")
        .attr("transform", "rotate(-90)")
        .attr("x", -height / 2)
        .attr("y", -40)
        .attr("text-anchor", "middle")
        .style("font-size", "14px")
        .text("Number of Users");
}
