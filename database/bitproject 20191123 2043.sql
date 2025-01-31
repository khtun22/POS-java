-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.0.45-community-nt


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema pos01
--

CREATE DATABASE IF NOT EXISTS pos01;
USE pos01;

--
-- Definition of table `brand`
--

DROP TABLE IF EXISTS `brand`;
CREATE TABLE `brand` (
  `brandid` varchar(15) NOT NULL,
  `brandname` varchar(45) NOT NULL,
  PRIMARY KEY  (`brandid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `brand`
--

/*!40000 ALTER TABLE `brand` DISABLE KEYS */;
INSERT INTO `brand` (`brandid`,`brandname`) VALUES 
 ('BR-0000001','brand001'),
 ('BR-0000002','brand0022'),
 ('BR-0000003','br003'),
 ('BR-0000004','br004'),
 ('BR-0000005','br005'),
 ('BR-0000006','br006'),
 ('BR-0000007','b07');
/*!40000 ALTER TABLE `brand` ENABLE KEYS */;


--
-- Definition of table `damageitem`
--

DROP TABLE IF EXISTS `damageitem`;
CREATE TABLE `damageitem` (
  `damageid` varchar(15) NOT NULL,
  `itemid` varchar(15) NOT NULL,
  `status` varchar(45) NOT NULL,
  `quantity` int(10) unsigned NOT NULL,
  `date` varchar(15) NOT NULL,
  PRIMARY KEY  USING BTREE (`damageid`),
  KEY `FK_damageitem_1` (`itemid`),
  CONSTRAINT `FK_damageitem_1` FOREIGN KEY (`itemid`) REFERENCES `itemdetail` (`itemid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `damageitem`
--

/*!40000 ALTER TABLE `damageitem` DISABLE KEYS */;
INSERT INTO `damageitem` (`damageid`,`itemid`,`status`,`quantity`,`date`) VALUES 
 ('DA-0000001','IT-0000001','f',15,'2019-11-19');
/*!40000 ALTER TABLE `damageitem` ENABLE KEYS */;


--
-- Definition of table `fitemdetail`
--

DROP TABLE IF EXISTS `fitemdetail`;
CREATE TABLE `fitemdetail` (
  `itemid` varchar(15) NOT NULL,
  `supplierid` varchar(15) NOT NULL,
  `date` datetime NOT NULL,
  `purchaseprice` bigint(20) unsigned NOT NULL,
  `purchaseqty` int(10) unsigned NOT NULL,
  KEY `FK_fitemdetail_1` (`itemid`),
  KEY `FK_fitemdetail_2` (`supplierid`),
  CONSTRAINT `FK_fitemdetail_1` FOREIGN KEY (`itemid`) REFERENCES `itemdetail` (`itemid`),
  CONSTRAINT `FK_fitemdetail_2` FOREIGN KEY (`supplierid`) REFERENCES `supplier` (`supplierid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `fitemdetail`
--

/*!40000 ALTER TABLE `fitemdetail` DISABLE KEYS */;
INSERT INTO `fitemdetail` (`itemid`,`supplierid`,`date`,`purchaseprice`,`purchaseqty`) VALUES 
 ('IT-0000001','SU-0000001','2018-07-31 00:00:00',13579,1),
 ('IT-0000004','SU-0000003','2018-07-31 00:00:00',3,2),
 ('IT-0000002','SU-0000005','2018-08-15 00:00:00',8,1),
 ('IT-0000003','SU-0000005','2018-08-15 00:00:00',9,1),
 ('IT-0000001','SU-0000005','2018-08-15 00:00:00',14936,1),
 ('IT-0000006','SU-0000005','2018-08-15 00:00:00',3,1),
 ('IT-0000007','SU-0000005','2018-08-15 00:00:00',2,1),
 ('IT-0000010','SU-0000005','2018-08-15 00:00:00',2,1),
 ('IT-0000001','SU-0000002','2018-08-15 00:00:00',16429,2),
 ('IT-0000003','SU-0000002','2018-08-15 00:00:00',9,2),
 ('IT-0000005','SU-0000002','2018-08-15 00:00:00',4,2),
 ('IT-0000006','SU-0000002','2018-08-15 00:00:00',3,2),
 ('IT-0000007','SU-0000002','2018-08-15 00:00:00',2,2),
 ('IT-0000001','SU-0000002','2018-08-15 00:00:00',16429,2),
 ('IT-0000009','SU-0000002','2018-08-15 00:00:00',3,2),
 ('IT-0000002','SU-0000005','2018-08-15 00:00:00',8,2),
 ('IT-0000003','SU-0000005','2018-08-15 00:00:00',9,2);
INSERT INTO `fitemdetail` (`itemid`,`supplierid`,`date`,`purchaseprice`,`purchaseqty`) VALUES 
 ('IT-0000004','SU-0000005','2018-08-15 00:00:00',3,1),
 ('IT-0000001','SU-0000004','2018-08-15 00:00:00',18071,1),
 ('IT-0000002','SU-0000004','2018-08-15 00:00:00',8,2),
 ('IT-0000002','SU-0000004','2018-08-15 00:00:00',8,2),
 ('IT-0000001','SU-0000004','2018-08-15 00:00:00',19878,2),
 ('IT-0000002','SU-0000002','2018-08-15 00:00:00',8,2),
 ('IT-0000001','SU-0000002','2018-08-15 00:00:00',21865,2),
 ('IT-0000005','SU-0000001','2018-08-15 00:00:00',4,2),
 ('IT-0000001','SU-0000001','2018-08-15 00:00:00',24051,1),
 ('IT-0000004','SU-0000003','2018-08-15 00:00:00',3,2),
 ('IT-0000002','SU-0000003','2018-08-15 00:00:00',8,2),
 ('IT-0000003','SU-0000003','2018-08-15 00:00:00',9,222),
 ('IT-0000003','SU-0000006','2018-08-15 00:00:00',9,2),
 ('IT-0000007','SU-0000006','2018-08-15 00:00:00',2,2),
 ('IT-0000002','SU-0000003','2018-08-15 00:00:00',8,2),
 ('IT-0000002','SU-0000005','2018-08-15 00:00:00',8,2),
 ('IT-0000002','SU-0000003','2018-08-15 00:00:00',8,2);
INSERT INTO `fitemdetail` (`itemid`,`supplierid`,`date`,`purchaseprice`,`purchaseqty`) VALUES 
 ('IT-0000002','SU-0000001','2018-08-15 00:00:00',8,2),
 ('IT-0000002','SU-0000003','2018-08-15 00:00:00',8,2),
 ('IT-0000005','SU-0000003','2018-08-15 00:00:00',4,2),
 ('IT-0000001','SU-0000006','2018-08-15 00:00:00',26456,2),
 ('IT-0000011','SU-0000006','2018-08-15 00:00:00',1,2),
 ('IT-0000001','SU-0000001','2018-08-15 00:00:00',29101,2),
 ('IT-0000006','SU-0000001','2018-08-15 00:00:00',3,2),
 ('IT-0000002','SU-0000004','2018-08-15 00:00:00',8,1),
 ('IT-0000001','SU-0000004','2018-08-15 00:00:00',32011,1),
 ('IT-0000001','SU-0000002','2018-08-15 00:00:00',35212,2),
 ('IT-0000002','SU-0000002','2018-08-15 00:00:00',8,2),
 ('IT-0000004','SU-0000002','2018-08-15 00:00:00',3,2),
 ('IT-0000009','SU-0000002','2018-08-15 00:00:00',3,2),
 ('IT-0000001','SU-0000002','2018-08-15 00:00:00',38733,2),
 ('IT-0000001','SU-0000002','2018-08-15 00:00:00',42606,2),
 ('IT-0000001','SU-0000002','2018-08-15 00:00:00',46866,2),
 ('IT-0000002','SU-0000002','2018-08-17 00:00:00',8,1);
INSERT INTO `fitemdetail` (`itemid`,`supplierid`,`date`,`purchaseprice`,`purchaseqty`) VALUES 
 ('IT-0000001','SU-0000002','2018-08-17 00:00:00',56707,1),
 ('IT-0000001','SU-0000003','2018-08-17 00:00:00',62377,2),
 ('IT-0000005','SU-0000003','2018-08-17 00:00:00',4,2),
 ('IT-0000003','SU-0000001','2018-08-17 00:00:00',9,2),
 ('IT-0000006','SU-0000001','2018-08-17 00:00:00',3,2),
 ('IT-0000003','SU-0000004','2018-08-17 00:00:00',9,2),
 ('IT-0000006','SU-0000004','2018-08-17 00:00:00',3,2),
 ('IT-0000003','SU-0000005','2018-08-17 00:00:00',9,2),
 ('IT-0000006','SU-0000005','2018-08-17 00:00:00',3,2),
 ('IT-0000004','SU-0000005','2018-08-17 00:00:00',3,2),
 ('IT-0000003','SU-0000004','2018-08-17 00:00:00',9,2),
 ('IT-0000005','SU-0000004','2018-08-17 00:00:00',4,2),
 ('IT-0000006','SU-0000004','2018-08-17 00:00:00',3,2),
 ('IT-0000003','SU-0000004','2018-08-17 00:00:00',9,2),
 ('IT-0000004','SU-0000005','2018-08-17 00:00:00',3,2),
 ('IT-0000004','SU-0000003','2018-08-17 00:00:00',3,2),
 ('IT-0000001','SU-0000003','2018-08-17 00:00:00',30000,22),
 ('IT-0000002','SU-0000003','2018-08-17 00:00:00',800,33);
INSERT INTO `fitemdetail` (`itemid`,`supplierid`,`date`,`purchaseprice`,`purchaseqty`) VALUES 
 ('IT-0000003','SU-0000003','2018-08-17 00:00:00',900,1),
 ('IT-0000004','SU-0000003','2018-08-17 00:00:00',100,2),
 ('IT-0000005','SU-0000003','2018-08-17 00:00:00',400,2),
 ('IT-0000006','SU-0000003','2018-08-17 00:00:00',300,2),
 ('IT-0000007','SU-0000003','2018-08-17 00:00:00',200,2),
 ('IT-0000008','SU-0000003','2018-08-17 00:00:00',100,11),
 ('IT-0000009','SU-0000003','2018-08-17 00:00:00',300,1),
 ('IT-0000010','SU-0000003','2018-08-17 00:00:00',200,111),
 ('IT-0000011','SU-0000003','2018-08-17 00:00:00',1000,1),
 ('IT-0000001','SU-0000005','2018-08-17 00:00:00',30000,1),
 ('IT-0000011','SU-0000005','2018-08-17 00:00:00',1000,2),
 ('IT-0000001','SU-0000001','2018-08-17 00:00:00',30000,2),
 ('IT-0000006','SU-0000002','2018-08-28 00:00:00',500,1),
 ('IT-0000007','SU-0000002','2018-08-28 00:00:00',300,2),
 ('IT-0000001','SU-0000007','2019-10-23 00:00:00',33000,122);
/*!40000 ALTER TABLE `fitemdetail` ENABLE KEYS */;


--
-- Definition of table `itemdetail`
--

DROP TABLE IF EXISTS `itemdetail`;
CREATE TABLE `itemdetail` (
  `itemid` varchar(15) NOT NULL,
  `itemname` varchar(45) NOT NULL,
  `brandid` varchar(15) NOT NULL,
  `typeid` varchar(15) NOT NULL,
  `curSaleprice` bigint(20) unsigned default NULL,
  `remark` varchar(45) NOT NULL,
  `totalQty` bigint(20) unsigned default NULL,
  `purchaseprice` bigint(20) unsigned NOT NULL,
  `pid` varchar(15) default NULL,
  PRIMARY KEY  (`itemid`),
  KEY `FK_itemdetail_1` (`brandid`),
  KEY `FK_itemdetail_2` (`typeid`),
  CONSTRAINT `FK_itemdetail_1` FOREIGN KEY (`brandid`) REFERENCES `brand` (`brandid`),
  CONSTRAINT `FK_itemdetail_2` FOREIGN KEY (`typeid`) REFERENCES `type` (`typeid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `itemdetail`
--

/*!40000 ALTER TABLE `itemdetail` DISABLE KEYS */;
INSERT INTO `itemdetail` (`itemid`,`itemname`,`brandid`,`typeid`,`curSaleprice`,`remark`,`totalQty`,`purchaseprice`,`pid`) VALUES 
 ('IT-0000001','Cola','BR-0000001','TY-0000001',36300,'111111111111111',1000071,33000,'PO-0000005'),
 ('IT-0000002','it002','BR-0000002','TY-0000002',900,'9999',100094,800,'PO-0000004'),
 ('IT-0000003','it003','BR-0000003','TY-0000003',1000,'33333333333333',10310,900,'PO-0000005'),
 ('IT-0000004','item04','BR-0000004','TY-0000004',200,'33333333333333',99995,100,''),
 ('IT-0000005','item005','BR-0000005','TY-0000005',500,'55555555555',100061,400,''),
 ('IT-0000006','666','BR-0000006','TY-0000006',600,'666',100065,500,''),
 ('IT-0000007','777','BR-0000006','TY-0000006',400,'777',100058,300,''),
 ('IT-0000008','888','BR-0000006','TY-0000006',200,'888',100060,100,''),
 ('IT-0000009','999','BR-0000006','TY-0000006',400,'999',100054,300,''),
 ('IT-0000010','10','BR-0000001','TY-0000001',300,'10',100161,200,''),
 ('IT-0000011','11','BR-0000001','TY-0000001',1100,'11',3,1000,'');
/*!40000 ALTER TABLE `itemdetail` ENABLE KEYS */;


--
-- Definition of table `promotion`
--

DROP TABLE IF EXISTS `promotion`;
CREATE TABLE `promotion` (
  `proid` varchar(15) NOT NULL,
  `ptid` varchar(15) NOT NULL,
  `startdate` varchar(15) NOT NULL,
  `enddate` varchar(15) NOT NULL,
  `pname` varchar(45) NOT NULL,
  `regdate` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `promotion`
--

/*!40000 ALTER TABLE `promotion` DISABLE KEYS */;
INSERT INTO `promotion` (`proid`,`ptid`,`startdate`,`enddate`,`pname`,`regdate`) VALUES 
 ('PO-0000001','PT-0000001','2019-11-18','2019-11-18','','2019-11-18'),
 ('PO-0000002','PT-0000001','2019-11-18','2019-11-18','','2019-11-18'),
 ('PO-0000003','PT-0000001','2019-11-18','2019-11-18','t','2019-11-18'),
 ('PO-0000004','PT-0000001','2019-11-18','2019-11-18','fo','2019-11-18'),
 ('PO-0000005','PT-0000001','2019-11-18','2019-11-18','fi','2019-11-18');
/*!40000 ALTER TABLE `promotion` ENABLE KEYS */;


--
-- Definition of table `promotiondetail`
--

DROP TABLE IF EXISTS `promotiondetail`;
CREATE TABLE `promotiondetail` (
  `itemid` varchar(15) default NULL,
  `proid` varchar(15) default NULL,
  `itemqty` int(10) unsigned default NULL,
  `dispri` int(10) unsigned default NULL,
  `proitemid` varchar(15) default NULL,
  `proitemqty` int(10) unsigned default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `promotiondetail`
--

/*!40000 ALTER TABLE `promotiondetail` DISABLE KEYS */;
INSERT INTO `promotiondetail` (`itemid`,`proid`,`itemqty`,`dispri`,`proitemid`,`proitemqty`) VALUES 
 ('IT-0000001','PO-0000002',1,330,NULL,NULL),
 ('IT-0000002','PO-0000002',1,90,NULL,NULL),
 ('IT-0000001','PO-0000002',2,33,NULL,NULL),
 ('IT-0000002','PO-0000002',1,90,NULL,NULL),
 ('IT-0000001','PO-0000002',1,330,NULL,NULL),
 ('IT-0000002','PO-0000002',1,90,NULL,NULL),
 ('IT-0000001','PO-0000002',1,3300,NULL,NULL),
 ('IT-0000002','PO-0000002',1,90,NULL,NULL),
 ('IT-0000001','PO-0000002',1,330,NULL,NULL),
 ('IT-0000002','PO-0000002',1,90,NULL,NULL),
 ('IT-0000001','PO-0000002',2,33,NULL,NULL),
 ('IT-0000002','PO-0000002',1,90,NULL,NULL),
 ('IT-0000001','PO-0000002',1,330,NULL,NULL),
 ('IT-0000002','PO-0000002',1,90,NULL,NULL),
 ('IT-0000001','PO-0000002',1,3300,NULL,NULL),
 ('IT-0000002','PO-0000002',1,90,NULL,NULL),
 ('IT-0000004','PO-0000002',1,20,NULL,NULL),
 ('IT-0000005','PO-0000002',1,50,NULL,NULL),
 ('IT-0000001','PO-0000004',1,330,NULL,NULL),
 ('IT-0000002','PO-0000004',1,90,NULL,NULL),
 ('IT-0000001','PO-0000005',1,33,NULL,NULL);
INSERT INTO `promotiondetail` (`itemid`,`proid`,`itemqty`,`dispri`,`proitemid`,`proitemqty`) VALUES 
 ('IT-0000003','PO-0000005',1,1000,NULL,NULL);
/*!40000 ALTER TABLE `promotiondetail` ENABLE KEYS */;


--
-- Definition of table `promotiontype`
--

DROP TABLE IF EXISTS `promotiontype`;
CREATE TABLE `promotiontype` (
  `ptid` varchar(15) NOT NULL,
  `ptname` varchar(45) NOT NULL,
  PRIMARY KEY  (`ptid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `promotiontype`
--

/*!40000 ALTER TABLE `promotiontype` DISABLE KEYS */;
INSERT INTO `promotiontype` (`ptid`,`ptname`) VALUES 
 ('PT-0000001','One Item Discount'),
 ('PT-0000002','Many Item in One Discount'),
 ('PT-0000003','Gift Discount');
/*!40000 ALTER TABLE `promotiontype` ENABLE KEYS */;


--
-- Definition of table `purchase`
--

DROP TABLE IF EXISTS `purchase`;
CREATE TABLE `purchase` (
  `purchaseid` varchar(15) NOT NULL,
  `supplierid` varchar(15) NOT NULL,
  `purchasedate` datetime NOT NULL,
  `staffid` varchar(15) NOT NULL,
  `totalamount` bigint(20) unsigned NOT NULL,
  PRIMARY KEY  (`purchaseid`),
  KEY `FK_purchase_1` (`supplierid`),
  KEY `FK_purchase_2` (`staffid`),
  CONSTRAINT `FK_purchase_1` FOREIGN KEY (`supplierid`) REFERENCES `supplier` (`supplierid`),
  CONSTRAINT `FK_purchase_2` FOREIGN KEY (`staffid`) REFERENCES `staff` (`staffid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `purchase`
--

/*!40000 ALTER TABLE `purchase` DISABLE KEYS */;
INSERT INTO `purchase` (`purchaseid`,`supplierid`,`purchasedate`,`staffid`,`totalamount`) VALUES 
 ('P-00000001','SU-0000001','2018-07-19 00:00:00','SF-0000001',0),
 ('P-00000002','SU-0000001','2018-07-19 00:00:00','SF-0000001',0),
 ('P-00000003','SU-0000001','2018-07-19 00:00:00','SF-0000001',0),
 ('P-00000004','SU-0000004','2018-07-19 00:00:00','SF-0000001',0),
 ('P-00000005','SU-0000005','2018-07-19 00:00:00','SF-0000001',0),
 ('P-00000006','SU-0000005','2018-07-19 00:00:00','SF-0000001',0),
 ('P-00000007','SU-0000005','2018-07-19 00:00:00','SF-0000001',0),
 ('P-00000008','SU-0000006','2018-07-20 00:00:00','SF-0000002',0),
 ('P-00000009','SU-0000005','2018-07-30 00:00:00','SF-0000001',0),
 ('P-00000010','SU-0000002','2018-07-30 00:00:00','SF-0000001',0),
 ('P-00000011','SU-0000001','2018-07-30 00:00:00','SF-0000001',0),
 ('P-00000012','SU-0000001','2018-07-30 00:00:00','SF-0000001',0),
 ('P-00000013','SU-0000002','2018-07-30 00:00:00','SF-0000001',0),
 ('P-00000014','SU-0000005','2018-07-31 00:00:00','SF-0000001',0),
 ('P-00000015','SU-0000006','2018-07-31 00:00:00','SF-0000001',0);
INSERT INTO `purchase` (`purchaseid`,`supplierid`,`purchasedate`,`staffid`,`totalamount`) VALUES 
 ('P-00000016','SU-0000002','2018-07-31 00:00:00','SF-0000001',0),
 ('P-00000017','SU-0000001','2018-07-31 00:00:00','SF-0000001',0),
 ('P-00000018','SU-0000001','2018-07-31 00:00:00','SF-0000001',0),
 ('P-00000019','SU-0000001','2018-07-31 00:00:00','SF-0000001',0),
 ('P-00000020','SU-0000002','2018-07-31 00:00:00','SF-0000001',0),
 ('P-00000021','SU-0000004','2018-07-31 00:00:00','SF-0000001',0),
 ('P-00000022','SU-0000001','2018-07-31 00:00:00','SF-0000001',0),
 ('P-00000023','SU-0000004','2018-07-31 00:00:00','SF-0000001',0),
 ('P-00000024','SU-0000004','2018-07-31 00:00:00','SF-0000001',0),
 ('P-00000025','SU-0000003','2018-07-31 00:00:00','SF-0000001',0),
 ('P-00000026','SU-0000002','2018-07-31 00:00:00','SF-0000001',0),
 ('P-00000027','SU-0000003','2018-07-31 00:00:00','SF-0000001',0),
 ('P-00000028','SU-0000001','2018-07-31 00:00:00','SF-0000002',0),
 ('P-00000029','SU-0000005','2018-07-31 00:00:00','SF-0000002',0),
 ('P-00000030','SU-0000005','2018-07-31 00:00:00','SF-0000001',0);
INSERT INTO `purchase` (`purchaseid`,`supplierid`,`purchasedate`,`staffid`,`totalamount`) VALUES 
 ('P-00000031','SU-0000001','2018-07-31 00:00:00','SF-0000002',0),
 ('P-00000032','SU-0000003','2018-07-31 00:00:00','SF-0000002',0),
 ('P-00000033','SU-0000005','2018-08-15 00:00:00','SF-0000002',0),
 ('P-00000034','SU-0000002','2018-08-15 00:00:00','SF-0000002',0),
 ('P-00000035','SU-0000005','2018-08-15 00:00:00','SF-0000002',0),
 ('P-00000036','SU-0000004','2018-08-15 00:00:00','SF-0000002',0),
 ('P-00000037','SU-0000004','2018-08-15 00:00:00','SF-0000002',0),
 ('P-00000038','SU-0000002','2018-08-15 00:00:00','SF-0000002',0),
 ('P-00000039','SU-0000001','2018-08-15 00:00:00','SF-0000002',0),
 ('P-00000040','SU-0000001','2018-08-15 00:00:00','SF-0000002',0),
 ('P-00000041','SU-0000003','2018-08-15 00:00:00','SF-0000002',0),
 ('P-00000042','SU-0000003','2018-08-15 00:00:00','SF-0000002',0),
 ('P-00000043','SU-0000003','2018-08-15 00:00:00','SF-0000002',0),
 ('P-00000044','SU-0000006','2018-08-15 00:00:00','SF-0000002',0),
 ('P-00000045','SU-0000003','2018-08-15 00:00:00','SF-0000002',0);
INSERT INTO `purchase` (`purchaseid`,`supplierid`,`purchasedate`,`staffid`,`totalamount`) VALUES 
 ('P-00000046','SU-0000005','2018-08-15 00:00:00','SF-0000002',0),
 ('P-00000047','SU-0000003','2018-08-15 00:00:00','SF-0000002',0),
 ('P-00000048','SU-0000001','2018-08-15 00:00:00','SF-0000002',0),
 ('P-00000049','SU-0000003','2018-08-15 00:00:00','SF-0000002',0),
 ('P-00000050','SU-0000006','2018-08-15 00:00:00','SF-0000002',0),
 ('P-00000051','SU-0000001','2018-08-15 00:00:00','SF-0000002',0),
 ('P-00000052','SU-0000004','2018-08-15 00:00:00','SF-0000002',0),
 ('P-00000053','SU-0000002','2018-08-15 00:00:00','SF-0000002',0),
 ('P-00000054','SU-0000002','2018-08-15 00:00:00','SF-0000002',0),
 ('P-00000055','SU-0000002','2018-08-15 00:00:00','SF-0000002',0),
 ('P-00000056','SU-0000002','2018-08-15 00:00:00','SF-0000002',0),
 ('P-00000057','SU-0000002','2018-08-15 00:00:00','SF-0000002',0),
 ('P-00000058','SU-0000002','2018-08-15 00:00:00','SF-0000002',0),
 ('P-00000059','SU-0000002','2018-08-15 00:00:00','SF-0000002',0),
 ('P-00000060','SU-0000002','2018-08-17 00:00:00','SF-0000002',0);
INSERT INTO `purchase` (`purchaseid`,`supplierid`,`purchasedate`,`staffid`,`totalamount`) VALUES 
 ('P-00000061','SU-0000003','2018-08-17 00:00:00','SF-0000002',0),
 ('P-00000062','SU-0000001','2018-08-17 00:00:00','SF-0000002',0),
 ('P-00000063','SU-0000004','2018-08-17 00:00:00','SF-0000002',0),
 ('P-00000064','SU-0000005','2018-08-17 00:00:00','SF-0000002',0),
 ('P-00000065','SU-0000005','2018-08-17 00:00:00','SF-0000002',0),
 ('P-00000066','SU-0000004','2018-08-17 00:00:00','SF-0000002',0),
 ('P-00000067','SU-0000004','2018-08-17 00:00:00','SF-0000002',0),
 ('P-00000068','SU-0000004','2018-08-17 00:00:00','SF-0000002',0),
 ('P-00000069','SU-0000004','2018-08-17 00:00:00','SF-0000002',0),
 ('P-00000070','SU-0000005','2018-08-17 00:00:00','SF-0000002',0),
 ('P-00000071','SU-0000003','2018-08-17 00:00:00','SF-0000002',0),
 ('P-00000072','SU-0000003','2018-08-17 00:00:00','SF-0000002',0),
 ('P-00000073','SU-0000005','2018-08-17 00:00:00','SF-0000002',0),
 ('P-00000074','SU-0000001','2018-08-17 00:00:00','SF-0000002',0),
 ('P-00000075','SU-0000002','2018-08-28 00:00:00','SF-0000002',1100);
INSERT INTO `purchase` (`purchaseid`,`supplierid`,`purchasedate`,`staffid`,`totalamount`) VALUES 
 ('P-00000076','SU-0000007','2019-10-23 00:00:00','SF-0000001',4026000);
/*!40000 ALTER TABLE `purchase` ENABLE KEYS */;


--
-- Definition of table `purchasedetail`
--

DROP TABLE IF EXISTS `purchasedetail`;
CREATE TABLE `purchasedetail` (
  `purchaseid` varchar(15) NOT NULL,
  `purchaseprice` bigint(20) unsigned NOT NULL,
  `purchasequantity` int(10) unsigned NOT NULL,
  `itemid` varchar(15) NOT NULL,
  KEY `FK_purchasedetail_1` (`purchaseid`),
  KEY `FK_purchasedetail_2` (`itemid`),
  CONSTRAINT `FK_purchasedetail_1` FOREIGN KEY (`purchaseid`) REFERENCES `purchase` (`purchaseid`),
  CONSTRAINT `FK_purchasedetail_2` FOREIGN KEY (`itemid`) REFERENCES `itemdetail` (`itemid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `purchasedetail`
--

/*!40000 ALTER TABLE `purchasedetail` DISABLE KEYS */;
INSERT INTO `purchasedetail` (`purchaseid`,`purchaseprice`,`purchasequantity`,`itemid`) VALUES 
 ('P-00000001',12345,2,'IT-0000001'),
 ('P-00000002',12345,22,'IT-0000001'),
 ('P-00000003',12345,2,'IT-0000001'),
 ('P-00000004',2,2,'IT-0000002'),
 ('P-00000005',2,33,'IT-0000002'),
 ('P-00000006',2,33,'IT-0000002'),
 ('P-00000007',2,33,'IT-0000002'),
 ('P-00000008',4,22,'IT-0000005'),
 ('P-00000009',9,11,'IT-0000003'),
 ('P-00000010',8,1,'IT-0000002'),
 ('P-00000011',12345,1,'IT-0000001'),
 ('P-00000011',8,1,'IT-0000002'),
 ('P-00000012',8,11,'IT-0000002'),
 ('P-00000013',4,2,'IT-0000005'),
 ('P-00000013',9,2,'IT-0000003'),
 ('P-00000014',9,11,'IT-0000003'),
 ('P-00000015',9,1,'IT-0000003'),
 ('P-00000016',8,2,'IT-0000002'),
 ('P-00000017',13579,22,'IT-0000001'),
 ('P-00000018',9,11,'IT-0000003'),
 ('P-00000019',8,22,'IT-0000002'),
 ('P-00000020',8,22,'IT-0000002'),
 ('P-00000021',9,22,'IT-0000003'),
 ('P-00000023',9,2,'IT-0000003'),
 ('P-00000023',4,2,'IT-0000005'),
 ('P-00000024',8,22,'IT-0000002'),
 ('P-00000025',8,1,'IT-0000002');
INSERT INTO `purchasedetail` (`purchaseid`,`purchaseprice`,`purchasequantity`,`itemid`) VALUES 
 ('P-00000025',9,1,'IT-0000003'),
 ('P-00000026',8,1,'IT-0000002'),
 ('P-00000026',9,2,'IT-0000003'),
 ('P-00000027',9,22,'IT-0000003'),
 ('P-00000027',4,1,'IT-0000005'),
 ('P-00000029',3,2,'IT-0000004'),
 ('P-00000029',4,22,'IT-0000005'),
 ('P-00000030',9,2,'IT-0000003'),
 ('P-00000031',13579,1,'IT-0000001'),
 ('P-00000032',3,2,'IT-0000004'),
 ('P-00000033',8,1,'IT-0000002'),
 ('P-00000033',9,1,'IT-0000003'),
 ('P-00000033',14936,1,'IT-0000001'),
 ('P-00000033',3,1,'IT-0000006'),
 ('P-00000033',2,1,'IT-0000007'),
 ('P-00000033',2,1,'IT-0000010'),
 ('P-00000034',16429,2,'IT-0000001'),
 ('P-00000034',9,2,'IT-0000003'),
 ('P-00000034',4,2,'IT-0000005'),
 ('P-00000034',3,2,'IT-0000006'),
 ('P-00000034',2,2,'IT-0000007'),
 ('P-00000034',16429,2,'IT-0000001'),
 ('P-00000034',3,2,'IT-0000009'),
 ('P-00000035',8,2,'IT-0000002'),
 ('P-00000035',9,2,'IT-0000003'),
 ('P-00000035',3,1,'IT-0000004'),
 ('P-00000036',18071,1,'IT-0000001'),
 ('P-00000036',8,2,'IT-0000002');
INSERT INTO `purchasedetail` (`purchaseid`,`purchaseprice`,`purchasequantity`,`itemid`) VALUES 
 ('P-00000037',8,2,'IT-0000002'),
 ('P-00000037',19878,2,'IT-0000001'),
 ('P-00000038',8,2,'IT-0000002'),
 ('P-00000038',21865,2,'IT-0000001'),
 ('P-00000039',4,2,'IT-0000005'),
 ('P-00000040',24051,1,'IT-0000001'),
 ('P-00000041',3,2,'IT-0000004'),
 ('P-00000042',8,2,'IT-0000002'),
 ('P-00000043',9,222,'IT-0000003'),
 ('P-00000044',9,2,'IT-0000003'),
 ('P-00000044',2,2,'IT-0000007'),
 ('P-00000045',8,2,'IT-0000002'),
 ('P-00000046',8,2,'IT-0000002'),
 ('P-00000047',8,2,'IT-0000002'),
 ('P-00000048',8,2,'IT-0000002'),
 ('P-00000049',8,2,'IT-0000002'),
 ('P-00000049',4,2,'IT-0000005'),
 ('P-00000050',26456,2,'IT-0000001'),
 ('P-00000050',1,2,'IT-0000011'),
 ('P-00000051',29101,2,'IT-0000001'),
 ('P-00000051',3,2,'IT-0000006'),
 ('P-00000052',8,1,'IT-0000002'),
 ('P-00000052',32011,1,'IT-0000001'),
 ('P-00000053',35212,2,'IT-0000001'),
 ('P-00000053',8,2,'IT-0000002'),
 ('P-00000054',3,2,'IT-0000004'),
 ('P-00000054',3,2,'IT-0000009');
INSERT INTO `purchasedetail` (`purchaseid`,`purchaseprice`,`purchasequantity`,`itemid`) VALUES 
 ('P-00000055',38733,2,'IT-0000001'),
 ('P-00000056',42606,2,'IT-0000001'),
 ('P-00000057',46866,2,'IT-0000001'),
 ('P-00000058',51552,2,'IT-0000001'),
 ('P-00000059',9,2,'IT-0000003'),
 ('P-00000060',8,1,'IT-0000002'),
 ('P-00000060',56707,1,'IT-0000001'),
 ('P-00000061',62377,2,'IT-0000001'),
 ('P-00000061',4,2,'IT-0000005'),
 ('P-00000062',9,2,'IT-0000003'),
 ('P-00000062',3,2,'IT-0000006'),
 ('P-00000063',9,2,'IT-0000003'),
 ('P-00000063',3,2,'IT-0000006'),
 ('P-00000064',9,2,'IT-0000003'),
 ('P-00000064',3,2,'IT-0000006'),
 ('P-00000065',3,2,'IT-0000004'),
 ('P-00000066',9,2,'IT-0000003'),
 ('P-00000067',4,2,'IT-0000005'),
 ('P-00000068',3,2,'IT-0000006'),
 ('P-00000069',9,2,'IT-0000003'),
 ('P-00000070',3,2,'IT-0000004'),
 ('P-00000071',3,2,'IT-0000004'),
 ('P-00000072',30000,22,'IT-0000001'),
 ('P-00000072',800,33,'IT-0000002'),
 ('P-00000072',900,1,'IT-0000003'),
 ('P-00000072',100,2,'IT-0000004'),
 ('P-00000072',400,2,'IT-0000005');
INSERT INTO `purchasedetail` (`purchaseid`,`purchaseprice`,`purchasequantity`,`itemid`) VALUES 
 ('P-00000072',300,2,'IT-0000006'),
 ('P-00000072',200,2,'IT-0000007'),
 ('P-00000072',100,11,'IT-0000008'),
 ('P-00000072',300,1,'IT-0000009'),
 ('P-00000072',200,111,'IT-0000010'),
 ('P-00000072',1000,1,'IT-0000011'),
 ('P-00000073',30000,1,'IT-0000001'),
 ('P-00000073',1000,2,'IT-0000011'),
 ('P-00000074',30000,2,'IT-0000001'),
 ('P-00000075',500,1,'IT-0000006'),
 ('P-00000075',300,2,'IT-0000007'),
 ('P-00000076',33000,122,'IT-0000001');
/*!40000 ALTER TABLE `purchasedetail` ENABLE KEYS */;


--
-- Definition of table `sale`
--

DROP TABLE IF EXISTS `sale`;
CREATE TABLE `sale` (
  `saleid` varchar(15) NOT NULL,
  `staffid` varchar(15) NOT NULL,
  `saledate` datetime NOT NULL,
  `totalamount` bigint(20) unsigned NOT NULL,
  PRIMARY KEY  (`saleid`),
  KEY `FK_sale_1` (`staffid`),
  CONSTRAINT `FK_sale_1` FOREIGN KEY (`staffid`) REFERENCES `staff` (`staffid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sale`
--

/*!40000 ALTER TABLE `sale` DISABLE KEYS */;
INSERT INTO `sale` (`saleid`,`staffid`,`saledate`,`totalamount`) VALUES 
 ('S-00000001','SF-0000001','2018-07-19 00:00:00',0),
 ('S-00000002','SF-0000001','2018-07-19 00:00:00',0),
 ('S-00000003','SF-0000001','2018-07-19 00:00:00',0),
 ('S-00000004','SF-0000001','2018-07-19 00:00:00',0),
 ('S-00000005','SF-0000001','2018-07-19 00:00:00',0),
 ('S-00000006','SF-0000005','2018-07-20 00:00:00',0),
 ('S-00000007','SF-0000003','2018-08-27 00:00:00',0),
 ('S-00000008','SF-0000003','2018-08-27 00:00:00',925800),
 ('S-00000009','SF-0000001','2019-11-23 00:00:00',68000);
/*!40000 ALTER TABLE `sale` ENABLE KEYS */;


--
-- Definition of table `saledetail`
--

DROP TABLE IF EXISTS `saledetail`;
CREATE TABLE `saledetail` (
  `saleid` varchar(15) NOT NULL,
  `saleprice` bigint(20) unsigned NOT NULL,
  `salequantity` int(10) unsigned NOT NULL,
  `itemid` varchar(15) NOT NULL,
  KEY `FK_saledetail_1` (`saleid`),
  KEY `FK_saledetail_2` (`itemid`),
  CONSTRAINT `FK_saledetail_1` FOREIGN KEY (`saleid`) REFERENCES `sale` (`saleid`),
  CONSTRAINT `FK_saledetail_2` FOREIGN KEY (`itemid`) REFERENCES `itemdetail` (`itemid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `saledetail`
--

/*!40000 ALTER TABLE `saledetail` DISABLE KEYS */;
INSERT INTO `saledetail` (`saleid`,`saleprice`,`salequantity`,`itemid`) VALUES 
 ('S-00000001',12345,22,'IT-0000001'),
 ('S-00000002',12345,33,'IT-0000001'),
 ('S-00000003',2,3,'IT-0000002'),
 ('S-00000004',2,3,'IT-0000002'),
 ('S-00000005',2,3,'IT-0000002'),
 ('S-00000006',12345,22,'IT-0000001'),
 ('S-00000007',33000,2,'IT-0000001'),
 ('S-00000007',900,22,'IT-0000002'),
 ('S-00000008',900,222,'IT-0000002'),
 ('S-00000008',33000,22,'IT-0000001'),
 ('S-00000009',33000,2,'IT-0000001'),
 ('S-00000009',1000,2,'IT-0000003');
/*!40000 ALTER TABLE `saledetail` ENABLE KEYS */;


--
-- Definition of table `staff`
--

DROP TABLE IF EXISTS `staff`;
CREATE TABLE `staff` (
  `staffid` varchar(15) NOT NULL,
  `name` varchar(45) NOT NULL,
  `address` varchar(45) NOT NULL,
  `phoneno` varchar(45) NOT NULL,
  `nrc` varchar(45) NOT NULL,
  `status` varchar(45) NOT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  PRIMARY KEY  (`staffid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `staff`
--

/*!40000 ALTER TABLE `staff` DISABLE KEYS */;
INSERT INTO `staff` (`staffid`,`name`,`address`,`phoneno`,`nrc`,`status`,`username`,`password`) VALUES 
 ('SF-0000001','name','address','099999','nrc','PSN','admin','admin'),
 ('SF-0000002','2','2','2','2','P','2','22'),
 ('SF-0000003','s003','3','3','3','S','3','33'),
 ('SF-0000004','s00444444','SA004','44444444444','SNRC004','O','4','444'),
 ('SF-0000005','5','5','5','55','PS','5','55'),
 ('SF-0000006','NatTharKwa','Yangon','08372618','12/TKJYOI(N)342684','S','ssnt','ssss');
/*!40000 ALTER TABLE `staff` ENABLE KEYS */;


--
-- Definition of table `supplier`
--

DROP TABLE IF EXISTS `supplier`;
CREATE TABLE `supplier` (
  `supplierid` varchar(15) NOT NULL,
  `name` varchar(45) NOT NULL,
  `address` varchar(100) NOT NULL,
  `phoneno` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  PRIMARY KEY  USING BTREE (`supplierid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `supplier`
--

/*!40000 ALTER TABLE `supplier` DISABLE KEYS */;
INSERT INTO `supplier` (`supplierid`,`name`,`address`,`phoneno`,`email`) VALUES 
 ('SU-0000001','sup01','1','1','1'),
 ('SU-0000002','su002','22','22','2'),
 ('SU-0000003','su003','33','3','3'),
 ('SU-0000004','su04','4','4','4'),
 ('SU-0000005','su05','5','5','5'),
 ('SU-0000006','su006','6','6','6'),
 ('SU-0000007','GyiKyaw','904 Street','09274837','NTT Company'),
 ('SU-0000008','shs','shshs','9281921','CC'),
 ('SU-0000009','','ss','928272','ss');
/*!40000 ALTER TABLE `supplier` ENABLE KEYS */;


--
-- Definition of table `temppro`
--

DROP TABLE IF EXISTS `temppro`;
CREATE TABLE `temppro` (
  `itemname` varchar(45) default NULL,
  `itemid` varchar(15) default NULL,
  `itemqty` int(10) unsigned default NULL,
  `dispri` int(10) unsigned default NULL,
  `proitemid` varchar(15) default NULL,
  `gitemname` varchar(45) default NULL,
  `gitemqty` int(10) unsigned default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `temppro`
--

/*!40000 ALTER TABLE `temppro` DISABLE KEYS */;
/*!40000 ALTER TABLE `temppro` ENABLE KEYS */;


--
-- Definition of table `type`
--

DROP TABLE IF EXISTS `type`;
CREATE TABLE `type` (
  `typeid` varchar(15) NOT NULL,
  `typename` varchar(45) NOT NULL,
  PRIMARY KEY  (`typeid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `type`
--

/*!40000 ALTER TABLE `type` DISABLE KEYS */;
INSERT INTO `type` (`typeid`,`typename`) VALUES 
 ('TY-0000001','Type001'),
 ('TY-0000002','type002'),
 ('TY-0000003','ty003'),
 ('TY-0000004','ty004'),
 ('TY-0000005','ty005'),
 ('TY-0000006','ty006');
/*!40000 ALTER TABLE `type` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
