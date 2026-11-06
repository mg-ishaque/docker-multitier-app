async function fetchMetrics() {
    const status = document.getElementById("status");

    try {
        const response = await fetch("/api/monitor");

        if (!response.ok) {
            throw new Error(`HTTP ${response.status}`);
        }

        const data = await response.json();

        status.innerText = data.status;
        document.getElementById("uptime").innerText =
            `${data.uptimeSeconds} seconds`;

        document.getElementById("memory-used").innerText =
            `${data.jvmMemoryUsedMB.toFixed(2)} MiB`;

        document.getElementById("memory-max").innerText =
            `${data.jvmMemoryMaxMB.toFixed(2)} MiB`;

        document.getElementById("last-updated").innerText =
            `Last updated: ${new Date().toLocaleTimeString()}`;

    } catch (error) {
        status.innerText = "UNREACHABLE";
        console.error("Monitoring API error:", error);
    }
}

fetchMetrics();

setInterval(fetchMetrics, 5000);