import sys
import csv
import pandas as pd
from datetime import datetime
import matplotlib.pyplot as plt
import matplotlib.dates as mdates

df = pd.read_csv(sys.argv[1], delimiter=',').sort_values(by=['x'])
print(df)
plt.plot(df[df.columns[0]], df[df.columns[1]])
plt.gcf().autofmt_xdate()
plt.show()
