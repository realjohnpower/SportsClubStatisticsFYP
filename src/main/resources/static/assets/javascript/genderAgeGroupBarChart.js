// Set up dimensions
function displayAgeGroupBarChart(data) {
    var margin = {top: 60, right: 30, bottom: 70, left: 60},
        width = 600 - margin.left - margin.right,
        height = 400 - margin.top - margin.bottom;

// Append SVG to the div
    var svg = d3.select("#gender-age-barchart")
        .append("svg")
        .attr("width", width + margin.left + margin.right)
        .attr("height", height + margin.top + margin.bottom)
        .append("g")
        .attr("transform", "translate(" + margin.left + "," + margin.top + ")");

// Extract age groups and gender categories
    var ageGroups = data.map(d => d.ageGroup);
    var genders = ["Male", "Female"];

// X scale (Age Groups)
    var x = d3.scaleBand()
        .domain(ageGroups)
        .range([0, width])
        .padding(0.2);

// Sub-scale for gender (Male, Female) within each age group
    var xSubgroup = d3.scaleBand()
        .domain(genders)
        .range([0, x.bandwidth()])
        .padding(0.05);

// Y scale
    var maxValue = d3.max(data, d => Math.max(d.Male, d.Female));
    var y = d3.scaleLinear()
        .domain([0, maxValue])
        .range([height, 0]);

// Color scale for Male/Female
    var color = d3.scaleOrdinal()
        .domain(genders)
        .range(["#1f77b4", "#ff7f0e"]); // Blue for Male, Orange for Female

// Add X Axis
    svg.append("g")
        .attr("transform", "translate(0," + height + ")")
        .call(d3.axisBottom(x));

// Add Y Axis
    svg.append("g")
        .call(d3.axisLeft(y));

// Draw bars
    svg.append("g")
        .selectAll("g")
        .data(data)
        .enter()
        .append("g")
        .attr("transform", d => "translate(" + x(d.ageGroup) + ",0)")
        .selectAll("rect")
        .data(d => genders.map(gender => ({key: gender, value: d[gender]})))
        .enter()
        .append("rect")
        .attr("x", d => xSubgroup(d.key))
        .attr("y", d => y(d.value))
        .attr("width", xSubgroup.bandwidth())
        .attr("height", d => height - y(d.value))
        .attr("fill", d => color(d.key));

    // ** Add Chart Title **
    svg.append("text")
        .attr("x", width / 2)
        .attr("y", -20)  // Adjust for top margin
        .attr("text-anchor", "middle")
        .style("font-size", "16px")
        .style("font-weight", "bold")
        .text("Age Group Distribution by Gender");  // <-- CUSTOM TITLE

    // ** Add X-axis Label **
    svg.append("text")
        .attr("x", width / 2)
        .attr("y", height + 50)  // Below X-axis
        .attr("text-anchor", "middle")
        .style("font-size", "14px")
        .text("Age Groups");  // <-- CUSTOM X-axis LABEL

    // ** Add Y-axis Label **
    svg.append("text")
        .attr("transform", "rotate(-90)") // Rotate text
        .attr("x", -height / 2)
        .attr("y", -50)  // Adjust position
        .attr("text-anchor", "middle")
        .style("font-size", "14px")
        .text("Number of Users");

// Add legend
    var legend = svg.append("g")
        .attr("transform", "translate(" + (width - 100) + ", 0)");

    genders.forEach((gender, i) => {
        legend.append("rect")
            .attr("x", 0)
            .attr("y", i * 20)
            .attr("width", 15)
            .attr("height", 15)
            .attr("fill", color(gender));

        legend.append("text")
            .attr("x", 20)
            .attr("y", i * 20+12)
            .text(gender)
            .attr("font-size", "12px")
            .attr("alignment-baseline", "middle");
    });
}