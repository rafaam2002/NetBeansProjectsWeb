const data = [
  1500, 2300, 2240, 2180, 1350, 1470, 2600, 1500, 2300, 2240, 2180, 1350, 1470,
  2600,
];
const xAxisData = [
  "Mon",
  "Tue",
  "Wed",
  "Thu",
  "Fri",
  "Sat",
  "Sun",
  "Mon",
  "Tue",
  "Wed",
  "Thu",
  "Fri",
  "Sat",
  "Sun",
];
// Número de días a mostrar inicialmente
const daysToShow = 7;
// Calcula el rango de visualización inicial
const startIndex = xAxisData.length - daysToShow;
const endIndex = xAxisData.length;
let MYCHART; //global para usarla en el resize

optionBasicLineFontChica = {
  title: {
    text: "Resumen Del Balance",
    left: "center",
    top: "7%",
    textStyle: {
      color: "#F0F0F3",
      fontWeight: "normal",
      fontFamily: "segoe ui",
    },
  },
  tooltip: {
    trigger: "axis",
    axisPointer: {
      type: "none",
    },
    formatter: (params) => {
      let tooltipContent = ""; // Inicializa el contenido del tooltip
      params.forEach((item) => {
        // Personaliza el contenido para cada serie de datos
        tooltipContent += "$  " + item.value;
      });
      return tooltipContent; // Devuelve el contenido personalizado
    },
  },

  xAxis: {
    type: "category",
    data: xAxisData,
    axisLabel: {
      fontSize: 14,
      color: "#d4d4d8",
    },
  },
  yAxis: {
    type: "value",
    axisLabel: {
      fontSize: 13,
      color: "#d4d4d8",
    },
  },
  series: [
    {
      data: data,
      type: "line",
      symbolSize: 10,
    },
  ],
  color: "#FFB700",
  //Barra controladora de dias display
  dataZoom: [
    {
      className: "custom-datazoom",
      type: "slider",
      start: 100 - (100 * daysToShow) / xAxisData.length,
      end: 100,
      zoomLook: true,
      handleIcon: "circle",
      handleSize: 15,
      backgroundColor: "#27272a80",
      dataBackground: {
        lineStyle: {
          color: "#3f3f46",
        },
        areaStyle: {
          color: "#d4d4d8",
        },
      },
      selectedDataBackground: {
        lineStyle: {
          color: "#FFCE52",
        },
        areaStyle: {
          color: "#C78F00",
          opacity: 0.8,
        },
      },
      brushSelect: false, //para quitar el arrastre con el click
    },
  ],
};

optionBasicLine = {
  title: {
    text: "Resumen Del Balance",
    left: "center",
    top: "7%",
    textStyle: {
      color: "#F0F0F3",
      fontWeight: "normal",
      fontFamily: "segoe ui",
    },
  },
  tooltip: {
    trigger: "axis",
    axisPointer: {
      type: "none",
    },
    formatter: (params) => {
      let tooltipContent = ""; // Inicializa el contenido del tooltip
      params.forEach((item) => {
        // Personaliza el contenido para cada serie de datos
        tooltipContent += "$  " + item.value;
      });
      return tooltipContent; // Devuelve el contenido personalizado
    },
  },

  xAxis: {
    type: "category",
    data: xAxisData,
    axisLabel: {
      fontSize: 14,
      color: "#d4d4d8",
    },
  },
  yAxis: {
    type: "value",
    axisLabel: {
      fontSize: 15,
      color: "#d4d4d8",
    },
  },
  series: [
    {
      data: data,
      type: "line",
      symbolSize: 10,
    },
  ],
  color: "#FFB700",
  //Barra controladora de dias display
  dataZoom: [
    {
      className: "custom-datazoom",
      type: "slider",
      start: 100 - (100 * daysToShow) / xAxisData.length,
      end: 100,
      zoomLook: true,
      handleIcon: "circle",
      handleSize: 15,
      backgroundColor: "#27272a80",
      dataBackground: {
        lineStyle: {
          color: "#3f3f46",
        },
        areaStyle: {
          color: "#d4d4d8",
        },
      },
      selectedDataBackground: {
        lineStyle: {
          color: "#FFCE52",
        },
        areaStyle: {
          color: "#C78F00",
          opacity: 0.8,
        },
      },
      brushSelect: false, //para quitar el arrastre con el click
    },
  ],
};

var optionPie = {
  tooltip: {
    trigger: "item",
  },
  legend: {
    show: false,
  },
  series: [
    {
      name: "Access From",
      type: "pie",
      radius: ["40%", "70%"],
      avoidLabelOverlap: false,
      label: {
        show: false,
      },
      emphasis: {
        focus: "self",
      },
      labelLine: {
        show: false,
      },
      tooltip: {
        //para el cuadradoBlanco
        formatter: "{d}%",
      },

      data: [
        { value: 950, name: "Capital", itemStyle: { color: "#808080" } }, // Gris un poco más oscuro
        { value: 50, name: "Gastos", itemStyle: { color: "#ff7f50" } }, // Rojo más intenso
        { value: 100, name: "Ingresos", itemStyle: { color: "#98fb98" } }, // Verde claro
      ],
    },
  ],
};
// Actualiza la serie con los nuevos datos

const createEcharts = () => {
  var anchoPantalla =
    window.innerWidth ||
    document.documentElement.clientWidth ||
    document.body.clientWidth;

  let myChart = echarts.init(document.querySelector("#grafica"));
  if (anchoPantalla > 768) {
    myChart.setOption(optionBasicLine);
  } else {
    myChart.setOption(optionBasicLineFontChica);
  }

  MYCHART = myChart;
  let myChartPie = echarts.init(document.querySelector("#grafica_pie"));
  myChartPie.setOption(optionPie);
};

window.addEventListener("DOMContentLoaded", (e) => {
  createEcharts();
});

window.addEventListener("resize", () => {
  MYCHART.resize();
  
});


