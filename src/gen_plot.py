import sys
import csv
import pandas as pd
from datetime import datetime
import matplotlib.pyplot as plt
import matplotlib.dates as mdates

df = pd.read_csv(sys.argv[1], delimiter=',').sort_values(by=['x'])
plt.plot(df[df.columns[0]], df[df.columns[1]])
plt.gcf().autofmt_xdate()
ax = plt.gca()
ax.set_xlim([-20, 100])
ax.set_ylim([-100, 100])
ax.grid(which='major', axis='both', linestyle='-')
plt.show()

