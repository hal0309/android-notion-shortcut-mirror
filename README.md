# 【WIP】 Notion Shortcut for Android

---

> [!CAUTION]  
> 本リポジトリは個人的用途の為一時的に公開したものです。  
> 関係者以外の閲覧、使用はご遠慮ください。  
> 
> This repository is temporary public for personal use.  
> Please do not use it without permission.


## 概要
Notionへの投稿を簡単に行うためのAndroidアプリです。(開発途中)
> [!NOTE] 
> 一部ファイルは公開していないためローカルでは動きません。  
> 試したい場合は直接ご連絡頂ければ対応します。


## ディレクトリ構成
> [!NOTE]  
> Androidエンジニアの方は読み飛ばして頂いても結構です。


### Kotlinでの記述があるディレクトリ

---
[`/app/src/main/java/com/smoothapp/notionshortcut`](/app/src/main/java/com/smoothapp/notionshortcut)  
本ディレクトにKotlinで記述されたソースコードが格納され、最も実装力を確認できると思います。

[`/controller`](/app/src/main/java/com/smoothapp/notionshortcut/controller) 
実際にAPIとの通信を担うクラスや、utilityクラスが格納されています。

[`/model`](/app/src/main/java/com/smoothapp/notionshortcut/model) 
主にデータを扱うクラスが格納されています。

[`/view`](/app/src/main/java/com/smoothapp/notionshortcut/view) 
主に画面表示を担うクラスが格納されています。



### その他リソース格納されているディレクトリ

---
[`/app/src/main/res`](/app/src/main/res)  
アプリで使用する各種リソースをxmlで定義しています。

[`/layout`](/app/src/main/res/layout)
画面構成のレイアウトファイルが格納されています。

[`/values`](/app/src/main/res/values)
色や言語の定義ファイルが格納されています。

## アピールポイント

### オブジェクト指向

---
[`/app/src/main/java/com/smoothapp/notionshortcut/model/entity/notiondatabaseproperty`](/app/src/main/java/com/smoothapp/notionshortcut/model/entity/notiondatabaseproperty)   

このディレクトリ内のデータクラス(ex. [`NotionDatabasePropertyMultiSelect`](/app/src/main/java/com/smoothapp/notionshortcut/model/entity/notiondatabaseproperty/NotionDatabasePropertyMultiSelect.kt))
は全て親クラス([`NotionDatabaseProperty`](/app/src/main/java/com/smoothapp/notionshortcut/model/entity/notiondatabaseproperty/NotionDatabaseProperty.kt))を継承している設計となっており、親子の相互変換が可能です。  
親クラスのデータはデータベースとの互換を意識し全てのデータを文字列配列に格納しているのに対し、子クラスでは利用に適したデータ型にキャストしています。

### カスタムview

---
[`/app/src/main/java/com/smoothapp/notionshortcut/view/component/notionshortcut`](/app/src/main/java/com/smoothapp/notionshortcut/view/component/notionshortcut/mainelement)  
[`/app/src/main/res/layout/view_*.xml`](/app/src/main/res/layout)

viewの処理をactivityやfragmentに全て集約するのでは無く、カスタムviewを作成し、view自体にに処理を行わせることにより、
可読性や再利用性が高い設計となっています。

### ソフトコーディング

---
[`/app/src/main/java/com/smoothapp/notionshortcut/model/constant`](/app/src/main/java/com/smoothapp/notionshortcut/model/constant)  

色やタイプ等の文字列定数をEnumにより管理したり、配列のインデックスを定数で管理することにより、
安全性が高い設計となっています。


### 文章量

---
[`/app/src/main/java/com/smoothapp/notionshortcut/view/activity`](/app/src/main/java/com/smoothapp/notionshortcut/view/activity)  
[`/app/src/main/java/com/smoothapp/notionshortcut/view/fragment`](/app/src/main/java/com/smoothapp/notionshortcut/view/fragment)  

これといったアピールではありませんが、比較的長いソースコードが格納されています。
特に[`ShortcutActivity`](/app/src/main/java/com/smoothapp/notionshortcut/view/activity/ShortcutActivity.kt)や[`NotionDateFragment`](/app/src/main/java/com/smoothapp/notionshortcut/view/fragment/NotionDateFragment.kt)が記述が多いです。  

## 追記
以下のような問題がありますが、今後拡充していきます。

* ドキュメントが未整理(まだリファクタリングが多いので後半にKDocでまとめます。)
* DBがまだ無い (Room Databaseを使用予定)
* 文字列のハードコーディングが多い (strings.xmlにまとめます)
