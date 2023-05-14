import sys
import csv
import pandas as pd
from datetime import datetime
import matplotlib.pyplot as plt
import matplotlib.dates as mdates

df = pd.read_csv(sys.argv[1], delimiter=',')
print(df)
plt.plot(df["X"], df["Result"])
plt.gcf().autofmt_xdate()
plt.show()
