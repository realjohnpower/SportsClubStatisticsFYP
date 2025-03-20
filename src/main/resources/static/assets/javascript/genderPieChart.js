var svg, x, y, margin, width, height, yAxisTitle, xAxisTitle;

function drawGenderChart(maleCount, femaleCount, yAxisLabel, xAxisLabel) {
    var data = [
        { group: "Male", value: +maleCount },
        { group: "Female", value: +femaleCount }
    ];

    var maxValue = d3.max(data, function(d) { return d.value; });

    margin = { top: 30, right: 30, bottom: 70, left: 60 };
    width = 460 - margin.left - margin.right;
    height = 400 - margin.top - margin.bottom;

    // Clear previous chart
    d3.select("#gender-barchart").select("svg").remove();

    // Create SVG
    svg = d3.select("#gender-barchart")
        .append("svg")
        .attr("width", width + margin.left + margin.right)
        .attr("height", height + margin.top + margin.bottom)
        .append("g")
        .attr("transform", "translate(" + margin.left + "," + margin.top + ")");

    // X Axis
    x = d3.scaleBand()
        .range([0, width])
        .domain(data.map(function(d) { return d.group; }))
        .padding(0.2);

    svg.append("g")
        .attr("transform", "translate(0," + height + ")")
        .call(d3.axisBottom(x));

    // Y Axis
    y = d3.scaleLinear()
        .domain([0, maxValue])
        .range([height, 0]);

    svg.append("g")
        .attr("class", "myYaxis")
        .call(d3.axisLeft(y));

    // X Label
    xAxisTitle = svg.append("text")
        .attr("x", width / 2)
        .attr("y", height + 40)
        .attr("text-anchor", "middle")
        .style("font-size", "14px")
        .text(xAxisLabel);

    // Y Label
    yAxisTitle = svg.append("text")
        .attr("class", "y-axis-title")
        .attr("transform", "rotate(-90)")
        .attr("x", -height / 2)
        .attr("y", -50)
        .attr("text-anchor", "middle")
        .style("font-size", "14px")
        .text(yAxisLabel);

    updateGenderChart(maleCount, femaleCount, yAxisLabel, xAxisLabel);
}

function updateGenderChart(maleCount, femaleCount, yAxisLabel, xAxisLabel) {
    var data = [
        { group: "Male", value: +maleCount },
        { group: "Female", value: +femaleCount }
    ];

    // Bind data to bars
    var bars = svg.selectAll("rect").data(data);

    // Append bars if needed, then merge and transition
    bars.enter()
        .append("rect")
        .merge(bars)
        .transition()
        .duration(1000)
        .attr("x", function(d) { return x(d.group); })
        .attr("y", function(d) { return y(d.value); })
        .attr("width", x.bandwidth())
        .attr("height", function(d) { return height - y(d.value); })
        .attr("fill", "#69b3a2");

    // Bind data to text labels for the bars
    var labels = svg.selectAll("text.label").data(data);

    // Append text elements for new data, merge with existing and update position
    labels.enter()
        .append("text")
        .attr("class", "label")
        .merge(labels)
        .transition()
        .duration(1000)
        .attr("x", function(d) { return x(d.group) + x.bandwidth()/2; })
        .attr("y", function(d) { return y(d.value) + 15; })
        .attr("text-anchor", "middle")
        .style("fill", "white")
        .style("font-size", "14px")
        .text(function(d) { return d.value; });

    // Update Axis Titles
    yAxisTitle.text(yAxisLabel);
    xAxisTitle.text(xAxisLabel);
}

// Example usage:
drawGenderChart(8, 12, "Number of Users", "Gender");
